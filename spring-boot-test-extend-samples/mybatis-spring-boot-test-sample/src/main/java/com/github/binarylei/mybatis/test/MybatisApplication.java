package com.github.binarylei.mybatis.test;

import com.github.binarylei.mybatis.test.mapper.UserMapper;
import com.github.binarylei.mybatis.test.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.binarylei.mybatis.test.mapper",
        annotationClass = Mapper.class)
public class MybatisApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MybatisApplication.class);

        UserMapper userMapper = context.getBean(UserMapper.class);
        userMapper.insertUser(new User(1L, "binarylei"));
        System.out.println(userMapper.selectUserById(1));
    }
}
