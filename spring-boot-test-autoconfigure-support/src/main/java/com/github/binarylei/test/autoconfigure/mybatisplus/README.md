# Mybatis-Plus Unit

同样使用 h2 内存数据库进行单元测试。`@MybatisPlusTest` 和 `@MybatisPlusDynamicDataSourceTest` 提供了手动装配的功能。

## Mybatis-Plus Unit 使用说明

详见 `mybatis-plus-spring-boot-test-smaple` 和 `mybatis-plus-dynamic-datasource-spring-test-sample` 工程

1. 引入 Maven 依赖

    ```xml
    <dependency>
        <groupId>com.github.binarylei</groupId>
        <artifactId>mybatis-plus-spring-boot-starter-test</artifactId>
        <version>0.1.2-beta</version>
        <scope>test</scope>
    </dependency>
    ```

2. 配置 schema.sql 和 data.sql 到 classpath(resources/) 目录下

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


3. 使用 `@MybatisPlusTest` 注解自动注入 Mybatis 测试环境

    ```java
    @MybatisPlusTest
    @MapperScan(basePackages = "com.github.binarylei.mybatisplus.mapper",
            annotationClass = Mapper.class)
    public class MybatisPlusConfig {
    }
    ```
    
4. 至此，你就可以将 Mybatis 无缝集成到你的测试环境中

    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = MybatisPlusConfig.class)
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

## 多数据源

详见 [mybatis-plus-dynamic-datasource-spring-test-sample](<https://github.com/binarylei/spring-boot-test-extend/tree/main/spring-boot-test-extend-samples/data-jdbc-spring-boot-test-sample>)

1. 配置文件

    ```yaml
    spring:
      datasource:
        dynamic:
          primary: master
          datasource:
            master:
              driver-class-name: org.h2.Driver
              # url必须的引号，否则会解析错误
              url: "jdbc:h2:mem:master;INIT=runscript from 'classpath:sql/master/schema.sql'\\;runscript from 'classpath:sql/master/data.sql'"
              password: root
              username: root
            slave:
              driver-class-name: org.h2.Driver
              url: "jdbc:h2:mem:slave;INIT=runscript from 'classpath:sql/slave/schema.sql'\\;runscript from 'classpath:sql/slave/data.sql'"
              password: root
              username: root
    ```

2. 使用 `@MybatisPlusDynamicDataSourceTest` 注解自动注入多数据源测试环境

    ```java
    @MybatisPlusDynamicDataSourceTest
    @MapperScan(basePackages = "com.github.binarylei.mybatisplus.dynamic.mapper",
            annotationClass = Mapper.class)
    @ComponentScan("com.github.binarylei.mybatisplus.dynamic.service")
    public class DynamicDataSourceConfig {
    }
    ```
    
3. 至此，你就可以将动态数据源无缝集成到你的测试环境中。不过缺陷就是对事务的支持不太好，实际使用时尽量不要在一个事务里操作两个数据源。