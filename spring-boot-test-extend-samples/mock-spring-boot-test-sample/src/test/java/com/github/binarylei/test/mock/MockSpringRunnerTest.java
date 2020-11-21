package com.github.binarylei.test.mock;

import com.github.binarylei.test.mock.config.MockSpringRunnerConfig;
import com.github.binarylei.test.mock.mapper.UserMapper;
import com.github.binarylei.test.mock.service.AuthService;
import com.github.binarylei.test.mock.service.MenuService;
import com.github.binarylei.test.mock.service.impl.MenuServiceImpl;
import com.github.binarylei.test.mock.service.impl.UserServiceImpl;
import com.github.binarylei.test.runner.MockSpringRunner;
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
    private UserMapper userMapper;                  // spring context √
    @Autowired
    private UserServiceImpl userService;            // spring context ×
    @Autowired
    private AuthService authService;                // spring context ×

    private MenuService menuService = new MenuServiceImpl();    // spring context ×
    private URI uri = URI.create("http://1.1.1.1"); // spring context √

    @Test
    public void testMockSpringRunner() {
        // 1. 注入字段在 spring context
        Assert.assertNotNull(userMapper);
        Assert.assertEquals("binarylei2", userMapper.selectById(2).getName());

        // 2. 注入字段在 spring context 没有时，如果是实现类，默认会先 new 后再进行属性注入
        Assert.assertNotNull(userService.getUserMapper());
        Assert.assertSame(userMapper, userService.getUserMapper());

        // 3. 注入字段在 spring context 没有时，如果是接口或抽象类，直接 mock
        Assert.assertNotNull(authService);
        when(authService.testMethod1()).thenReturn("binarylei");
        Assert.assertEquals("binarylei", authService.testMethod1());

        // 4. 属性注入时，testInstance 中定义的字段替换 spring context 中的 bean
        Assert.assertSame(uri, userService.getUri());
        Assert.assertSame(menuService, userService.getMenuTestService());
    }

}
