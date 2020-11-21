# MockSpringRunner Unit

## MockSpringRunner Unit 功能说明

实现自定义的 MockSpringRunner，参考{@see SpringRunner}。MockSpringRunner 扩展了 TestInstance 中依赖注入的来源：spring + mockito

<li>1. 如果 spring 容器中没有，则根据字段类型实例化该 bean，并自动完成属性注入</li>
<li>2. 依赖注入来源的优先级如下： xxxTest > spring context > mock</li>

## MockSpringRunner Unit 使用说明

详见 `mock-spring-boot-test-sample` 工程

1. 引入 Maven 依赖

```xml
<dependency>
    <groupId>com.github.binarylei</groupId>
    <artifactId>spring-boot-test-autoconfigure-support</artifactId>
    <version>0.1.2-beta</version>
</dependency>
```

2. 使用 MockSpringRunner 替换 SpringRunner

```java
@RunWith(MockSpringRunner.class)
public class MockSpringRunnerTest {
}
```

3. 依赖注入的来源如下

依赖注入来源的优先级如下： <b>xxxTest > spring context > mock</b>

```java
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

@Configuration
@AutoConfigureMybatisPlus
@MapperScan(basePackages = "com.github.binarylei.test.mock.mapper",
        annotationClass = Mapper.class)
public static class MockSpringRunnerConfig {
    @Bean
    public URI url() {
        return URI.create("http://127.0.0.1");
    }
}
```
