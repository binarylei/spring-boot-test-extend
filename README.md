# spring-boot-test-extend

目前只支持 Redis 的有限操作，后续计划增加对 Kafka、mybatis-plus 等常见外部资源单元测试的依赖，争取做到常见外部资源的自动装配，简化单元测试。

1. [MockSpringRunner Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/runner>)

2. [Mybatis Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/mybatis>)

3. [Mybatis-Plus Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/mybatisplus>)

4. [Redis Unit 使用说明](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-autoconfigure-support/src/main/java/com/github/binarylei/test/autoconfigure/redis>)

### Redis Unit 使用说明

详见 `spring-boot-test-data-redis-sample` 工程

1. 使用 `@RedisTest` 注解自动注入 Redis 测试环境

```java
@Configuration
@RedisTest
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

```text
spring-boot-test-extend/          # 项目根目录（有 package.json）
├── package.json                  # 项目依赖配置（核心）
├── docs/                         # 文档源文件目录
│   ├── .vuepress/                # VuePress 配置目录
│   │   ├── config.js             # 文档配置
│   │   └── dist/                 # 构建后的静态文件（自动生成）
│   └── README.md                 # 文档内容
└── dev/
    └── deploy_gh_pages.sh        # 部署脚本
```
