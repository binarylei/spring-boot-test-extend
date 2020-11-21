package com.github.binarylei.test.runner;

/**
 * 实现自定义的 JunitRunner，参考{@link org.springframework.test.context.junit4.SpringRunner}<p>
 *
 * <h2>扩展 {@see @Autowire} 注解功能：spring + mockito</h2>
 * <li>1. 如果 spring 容器中没有，则根据字段类型实例化该 bean，并自动完成属性注入</li>
 * <li>2. 属性注入来源如下：优先级 xxxTest > spring context > mock</li>
 *
 * <pre>
 *      @RunWith(MockSpringRunner.class)
 *      public class MockSpringRunnerTest {
 *      }
 * </pre>
 */