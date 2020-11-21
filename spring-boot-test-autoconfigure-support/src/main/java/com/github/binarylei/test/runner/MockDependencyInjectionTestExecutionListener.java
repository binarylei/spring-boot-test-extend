package com.github.binarylei.test.runner;

import com.github.binarylei.test.runner.support.TestApplications;
import org.mockito.Mockito;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @author binarylei
 * @version 2020-11-15
 */
public class MockDependencyInjectionTestExecutionListener extends
        DependencyInjectionTestExecutionListener {

    @Override
    protected void injectDependencies(TestContext testContext) throws Exception {
        Object testInstance = testContext.getTestInstance();
        Class<?> testType = testContext.getTestClass();

        // 1. 构建 TestDefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) testContext
                .getApplicationContext().getAutowireCapableBeanFactory();
        TestDefaultListableBeanFactory testBeanFactory = new TestDefaultListableBeanFactory(testInstance);
        testBeanFactory.setParentBeanFactory(beanFactory);

        // 2. 完成依赖注入
        testBeanFactory.autowireBeanProperties(testInstance,
                AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        testBeanFactory.initializeBean(testInstance,
                testType.getName() + AutowireCapableBeanFactory.ORIGINAL_INSTANCE_SUFFIX);
        testContext.removeAttribute(REINJECT_DEPENDENCIES_ATTRIBUTE);
    }

    public class TestDefaultListableBeanFactory extends DefaultListableBeanFactory {

        private DefaultListableBeanFactory parentBeanFactory;

        private TestDefaultListableBeanFactory(Object testInstance) {
            // 1. 自动注入后置处理器
            AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor =
                    new AutowiredAnnotationBeanPostProcessor();
            autowiredAnnotationBeanPostProcessor.setBeanFactory(this);
            addBeanPostProcessor(autowiredAnnotationBeanPostProcessor);

            // 2. testInstance实际上就是TestDefaultListableBeanFactory的配置类
            configureBeanFactory(testInstance);

            // 3. 初始化化TestApplications
            registerBeanDefinition(TestApplications.class.getSimpleName(), BeanDefinitionBuilder
                    .rootBeanDefinition(TestApplications.class).getBeanDefinition());
            getBean(TestApplications.class);
        }

        @Override
        public Object doResolveDependency(DependencyDescriptor descriptor, String beanName,
                Set<String> autowiredBeanNames, TypeConverter typeConverter) throws BeansException {

            // testInstance > spring context > mock
            try {
                return super.doResolveDependency(descriptor, beanName, autowiredBeanNames, typeConverter);
            } catch (BeansException e) {
                String dependencyName = descriptor.getDependencyName();
                Class<?> dependencyType = descriptor.getDependencyType();

                registerBeanDefinition(dependencyName, BeanDefinitionBuilder
                        .rootBeanDefinition(dependencyType).getBeanDefinition());
                return getBean(dependencyName);
            }
        }

        /**
         * 更新instantiateBean的策略，如果是interface或abstract，则直接mock
         */
        @Override
        protected BeanWrapper instantiateBean(final String beanName, final RootBeanDefinition mbd) {
            Class<?> classType = mbd.getTargetType();
            if (classType != null && (Modifier.isInterface(classType.getModifiers()) ||
                    Modifier.isAbstract(classType.getModifiers()))) {
                BeanWrapper bw = new BeanWrapperImpl(Mockito.mock(classType));
                initBeanWrapper(bw);
                return bw;
            }

            return super.instantiateBean(beanName, mbd);
        }

        /**
         * 将 testInstance 中字段作为 bean 注册到容器中，会替换 spring context 中的上下文环境
         *
         * @param testInstance 相当于TestDefaultListableBeanFactory的配置类
         */
        private void configureBeanFactory(Object testInstance) {
            Class<?> targetClass = testInstance.getClass();
            do {
                ReflectionUtils.doWithLocalFields(targetClass, field -> {
                    field.setAccessible(true);
                    Object value = field.get(testInstance);

                    if (value != null) {
                        registerSingleton(field.getName(), value);
                    }
                });
                targetClass = targetClass.getSuperclass();
            }
            while (targetClass != null && targetClass != Object.class);
        }
    }
}