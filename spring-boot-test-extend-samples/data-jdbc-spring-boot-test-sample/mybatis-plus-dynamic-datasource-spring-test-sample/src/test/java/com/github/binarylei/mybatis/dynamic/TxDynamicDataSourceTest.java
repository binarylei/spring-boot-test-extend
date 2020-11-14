package com.github.binarylei.mybatis.dynamic;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatis.dynamic.mapper.UserMapper;
import com.github.binarylei.mybatis.dynamic.service.UserService2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Transactional
@Rollback
public class TxDynamicDataSourceTest extends SpringBootTestFramework {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService2 userService;

    @BeforeClass
    public static void setUp() throws Exception {
        DynamicDataSourceContextHolder.push("master");
    }

    @Test
    @Sql(statements = "insert into user values(3, 'binarylei3');",
            /*executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,*/
            config = @SqlConfig(dataSource = "master", transactionManager = "masterTransactionManager"))
    public void testTx() {
        User user = userMapper.selectById(3);
        Assert.assertEquals("binarylei3", user.getName());
    }

    @Test
    public void testTxNotExists() {
        Assert.assertNull(userMapper.selectById(3));
    }


    @Test
    public void test() {
        userService.save(new User(5, "binarylei5"));
    }
}
