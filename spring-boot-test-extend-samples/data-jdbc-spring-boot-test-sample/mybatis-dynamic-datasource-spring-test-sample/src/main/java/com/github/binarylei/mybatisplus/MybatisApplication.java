package com.github.binarylei.mybatisplus;

import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatisplus.config.DynamicDataSourceConfig;
import com.github.binarylei.mybatisplus.mapper.MasterUserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication();
//        springApplication.addPrimarySources(Arrays.asList(DynamicDataSourceConfig.class,));
        ApplicationContext context = SpringApplication.run(MybatisApplication.class);

        MasterUserMapper masterUserMapper = context.getBean(MasterUserMapper.class);
        masterUserMapper.insertUser(new User(1L, "binarylei"));
        System.out.println(masterUserMapper.selectUserById(1));
    }
}
