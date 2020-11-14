package com.github.binarylei.mybatis.test;

import com.github.binarylei.mybatis.test.config.MybatisConfig;
import com.github.binarylei.mybatis.test.mapper.UserMapper;
import com.github.binarylei.mybatis.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开启 ${link @@Transactional} 事务后自动回滚，注意mysql必须是innodb
 *
 * @author binarylei
 * @version 2020-11-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisConfig.class)
@Transactional
public class TxMybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testTx() {
        Assert.assertNull(userMapper.selectUserById(3L));
        userMapper.insertUser(new User(3L, "binarylei3"));
        Assert.assertEquals("binarylei3", userMapper.selectUserById(3L).getName());
    }

    @Test
    @Sql(statements = "insert into user(id,name) values(4,'binarylei4')")
    public void testSql() {
        Assert.assertEquals("binarylei4", userMapper.selectUserById(4L).getName());
    }

    @Test
    public void testTx3() {
        Assert.assertNull(userMapper.selectUserById(3L));
    }
}
