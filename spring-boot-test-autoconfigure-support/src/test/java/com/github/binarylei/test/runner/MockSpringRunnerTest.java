package com.github.binarylei.test.runner;

import com.github.binarylei.test.runner.config.MockSpringRunnerConfig;
import com.github.binarylei.test.runner.mapper.UserTestBeanMapper;
import com.github.binarylei.test.runner.service.AuthTestService;
import com.github.binarylei.test.runner.service.MenuTestService;
import com.github.binarylei.test.runner.service.impl.MenuTestServiceImpl;
import com.github.binarylei.test.runner.service.impl.UserTestBeanServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.net.URI;

import static org.mockito.Mockito.when;


/**
 * @author binarylei
 * @version 2020-11-15
 */
@RunWith(MockSpringRunner.class)
@ContextConfiguration(classes = MockSpringRunnerConfig.class)
public class MockSpringRunnerTest {

    @Autowired
    private UserTestBeanMapper userTestBeanMapper;          // spring context √
    @Autowired
    private UserTestBeanServiceImpl userTestBeanService;    // spring context ×
    @Autowired
    private AuthTestService authTestService;                // spring context ×

    private MenuTestService menuTestService = new MenuTestServiceImpl();    // spring context ×
    private URI uri = URI.create("http://1.1.1.1");         // spring context √

    @Test
    public void testMockSpringRunner() {
        // 1. 注入字段在 spring context
        Assert.assertNotNull(userTestBeanMapper);
        Assert.assertEquals("binarylei2", userTestBeanMapper.selectById(2).getName());

        // 2. 注入字段在 spring context 没有时，如果是实现类，默认会先 new 后再进行属性注入
        Assert.assertNotNull(userTestBeanService.getUserTestBeanMapper());
        Assert.assertSame(userTestBeanMapper, userTestBeanService.getUserTestBeanMapper());

        // 3. 注入字段在 spring context 没有时，如果是接口或抽象类，直接 mock
        Assert.assertNotNull(authTestService);
        when(authTestService.testMethod1()).thenReturn("binarylei");
        Assert.assertEquals("binarylei", authTestService.testMethod1());

        // 4. 属性注入时，testInstance 中定义的字段替换 spring context 中的 bean
        Assert.assertSame(uri, userTestBeanService.getUri());
        Assert.assertSame(menuTestService, userTestBeanService.getMenuTestService());
    }

}
