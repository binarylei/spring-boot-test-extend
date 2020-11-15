package com.github.binarylei.mybatis.dynamic;

import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatis.dynamic.mapper.MasterUserMapper;
import com.github.binarylei.mybatis.dynamic.mapper.SlaveUserMapper;
import org.junit.Assert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MybatisApplication.class);

        MasterUserMapper masterUserMapper = context.getBean(MasterUserMapper.class);
        masterUserMapper.deleteUserById(1L);
        masterUserMapper.insertUser(new User(1L, "binarylei"));
        Assert.assertEquals("binarylei", masterUserMapper.selectUserById(1).getName());


        SlaveUserMapper slaveUserMapper = context.getBean(SlaveUserMapper.class);
        slaveUserMapper.deleteUserById(2L);
        slaveUserMapper.insertUser(new User(2L, "binarylei2"));
        Assert.assertEquals("binarylei2", slaveUserMapper.selectUserById(2L).getName());
    }
}
