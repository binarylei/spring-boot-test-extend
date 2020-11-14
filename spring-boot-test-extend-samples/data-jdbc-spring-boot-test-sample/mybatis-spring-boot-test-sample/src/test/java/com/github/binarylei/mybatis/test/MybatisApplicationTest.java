package com.github.binarylei.mybatis.test;

import com.github.binarylei.mybatis.test.config.MybatisConfig;
import com.github.binarylei.mybatis.test.mapper.UserMapper;
import com.github.binarylei.mybatis.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisConfig.class)
public class MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void test() {
        User user = userMapper.selectUserById(2L);
        Assert.assertEquals("binarylei2", user.getName());
    }

}
