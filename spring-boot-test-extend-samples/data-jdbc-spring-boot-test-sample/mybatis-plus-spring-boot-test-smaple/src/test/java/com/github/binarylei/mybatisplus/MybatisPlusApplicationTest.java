package com.github.binarylei.mybatisplus;

import com.github.binarylei.mybatisplus.config.MybatisPlusConfig;
import com.github.binarylei.mybatisplus.mapper.UserMapper;
import com.github.binarylei.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusConfig.class)
public class MybatisPlusApplicationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = userMapper.selectById(2L);
        Assert.assertEquals("binarylei2", user.getName());
    }
}
