package com.github.binarylei.test.runner.support;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

/**
 * @author binarylei
 * @version 2020-11-21
 */
public class TestApplications implements BeanFactoryAware {

    public static final ThreadLocal<BeanFactory> beanFactoryThreadLocal = new ThreadLocal<>();

    public static Object getBean(String name) throws BeansException {
        return beanFactoryThreadLocal.get().getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return beanFactoryThreadLocal.get().getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        assertNotNull();
        return beanFactoryThreadLocal.get().getBean(requiredType);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        beanFactoryThreadLocal.set(beanFactory);
        LoggerFactory.getLogger(this.getClass()).info("TestApplications init...");
    }

    public static void set(BeanFactory beanFactory) {
        beanFactoryThreadLocal.set(beanFactory);
    }

    public static void setConfigClass(Class<?>... annotatedClasses) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(annotatedClasses);
        beanFactoryThreadLocal.set(context.getAutowireCapableBeanFactory());
    }

    private static void assertNotNull() {
        Assert.notNull(beanFactoryThreadLocal.get(), "beanFactory can't bu null");
    }

}
