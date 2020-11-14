package com.github.binarylei.mybatis.test;

import com.github.binarylei.mybatis.test.config.MybatisConfig;
import com.github.binarylei.mybatis.test.mapper.UserMapper;
import com.github.binarylei.mybatis.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author binarylei
 * @version 2020-11-12
 */
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
