# spring-boot-test-extend

目前只支持 Redis 的有限操作，后续计划增加对 Kafka、mybatis-plus 等常见外部资源单元测试的依赖，争取做到常见外部资源的自动装配，简化单元测试。

1. [MockSpringRunner Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/runner>)

2. [Mybatis Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/mybatis>)

3. [Mybatis-Plus Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/mybatisplus>)

4. [Redis Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/redis>)

### Redis Unit 使用说明

详见 `spring-boot-test-data-redis-sample` 工程

1. 使用 `@AutoConfigureTestRedis` 注解自动注入 Redis 测试环境

```java
@Configuration
@AutoConfigureTestRedis
public class RedisConfig {
}
```

2. 至此，你就可以将 RedisTemplate 无缝集成到你的测试环境中

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
