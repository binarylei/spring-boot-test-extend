# Mybatis Unit

使用 h2 内存数据库进行单元测试。`{@see @MybatisTest}` 提供了手动装配的功能。

## Mybatis Unit 使用说明

详见 `mybatis-spring-boot-test-sample` 和 `mybatis-dynamic-datasource-spring-test-sample` 工程

1. 引入 Maven 依赖

    ```xml
    <dependency>
        <groupId>com.github.binarylei</groupId>
        <artifactId>mybatis-spring-boot-starter-test</artifactId>
        <version>0.1.2-beta</version>
        <scope>test</scope>
    </dependency>
    ```

2. 使用 `@MybatisTest` 注解自动注入 Mybatis 测试环境

    ```java
    @MybatisTest
    @MapperScan(basePackages = "com.github.binarylei.mybatis.test.mapper",
            annotationClass = Mapper.class)
    public class MybatisConfig {
    }
    ```

3. 配置 schema.sql 和 data.sql 到 classpath(resources/) 目录下

    ```sql
    -- schema.sql
    DROP TABLE IF EXISTS user;
    CREATE TABLE user (
      id bigint(20) NOT NULL AUTO_INCREMENT,
      name varchar(255) DEFAULT NULL,
      PRIMARY KEY (id)
    );
    
    -- data.sql
    insert into user values(2, 'binarylei2');
    ```

4. 至此，你就可以将 Mybatis 无缝集成到你的测试环境中

    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = MybatisConfig.class)
    public class MybatisApplicationTest {
        @Autowired
        private UserMapper userMapper;
    
        @Test
        public void test() {
            User user = userMapper.selectUserById(2L);
            Assert.assertEquals("binarylei2", user.getName());
        }
    }
    ```

## 事务测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisConfig.class)
@Transactional
public class TxMybatisApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        Assert.assertNull(userMapper.selectUserById(3L));
    }
    
    @Test
    public void testTx() {
        Assert.assertNull(userMapper.selectUserById(3L));
        userMapper.insertUser(new User(3L, "binarylei3"));
        Assert.assertEquals("binarylei3", userMapper.selectUserById(3L).getName());
    }
    
    // 插入测试数据，单元测试完成后自动回滚
    @Test
    @Sql(statements = "insert into user(id,name) values(4,'binarylei4')")
    public void testSql() {
        Assert.assertEquals("binarylei4", userMapper.selectUserById(4L).getName());
    }
}
```

## 多数据源

详见 [mybatis-dynamic-datasource-spring-test-sample](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-extend-samples/data-jdbc-spring-boot-test-sample>)

```properties
spring.datasource.master.driver-class-name=org.h2.Driver
#jdbc-url：properties不能加引号，而yaml则必须加引号？？？
spring.datasource.master.jdbc-url=jdbc:h2:mem:master;INIT=runscript from 'classpath:sql/master/schema.sql'\\;runscript from 'classpath:sql/master/data.sql'
spring.datasource.master.password=root
spring.datasource.master.username=root

spring.datasource.slave.driver-class-name=org.h2.Driver
spring.datasource.slave.jdbc-url=jdbc:h2:mem:slave;INIT=runscript from 'classpath:sql/slave/schema.sql'\\;runscript from 'classpath:sql/slave/data.sql'
spring.datasource.slave.username=root
spring.datasource.slave.password=root
```
