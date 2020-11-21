# spring-boot-test-extend

目前只支持 Redis 的有限操作，后续计划增加对 Kafka、mybatis-plus 等常见外部资源单元测试的依赖，争取做到常见外部资源的自动装配，简化单元测试。

## Redis Unit

### Redis Unit 使用说明

详见 `spring-boot-test-data-redis-sample` 工程

1. 引入 Maven 依赖

```xml
<dependency>
    <groupId>com.github.binarylei</groupId>
    <artifactId>spring-boot-starter-test-data-redis</artifactId>
    <version>0.1.2-beta</version>
</dependency>
```

2. 使用 `@AutoConfigureTestRedis` 注解自动注入 Redis 测试环境

```java
@Configuration
@AutoConfigureTestRedis
public class RedisConfig {
}
```

3. 至此，你就可以将 RedisTemplate 无缝集成到你的测试环境中

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisConfig.class)
public class AutoConfigureRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testAutoConfigureRedis() {
        redisTemplate.opsForValue().set("key1", "value1");
        Assert.assertEquals("value1", redisTemplate.opsForValue().get("key1"));
    }
}
```

### Redis Unit RoadMap

目前 `LocalRedisConnection` 只支持有限的操作，后续会根据需求逐步扩展：
1. `LocalRedisKeyCommands`
2. `LocalRedisServerCommands`
3. `LocalRedisStringCommands`
