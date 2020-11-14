package com.github.binarylei.mybatis.plus;

import com.github.binarylei.mybatis.plus.mapper.UserMapper;
import com.github.binarylei.mybatis.plus.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Assert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.binarylei.mybatis.plus.mapper",
        annotationClass = Mapper.class)
public class MybatisPlusApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MybatisPlusApplication.class);

        UserMapper userMapper = context.getBean(UserMapper.class);
        userMapper.deleteById(1L);
        userMapper.insert(new User(1L, "binarylei"));
        Assert.assertEquals("binarylei", userMapper.selectById(1).getName());
    }
}
