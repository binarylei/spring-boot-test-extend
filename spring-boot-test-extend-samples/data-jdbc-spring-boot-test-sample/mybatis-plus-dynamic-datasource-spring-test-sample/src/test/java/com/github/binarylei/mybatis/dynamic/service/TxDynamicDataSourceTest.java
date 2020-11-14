package com.github.binarylei.mybatis.dynamic.service;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatis.dynamic.SpringBootTestFramework;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * 多数据源时，事务如何处理
 *
 * @author binarylei
 * @version 2020-11-14
 */
//@Rollback  // 默认回滚
@Transactional
public class TxDynamicDataSourceTest extends SpringBootTestFramework {

    @Autowired
    private UserTxService userService;

    @BeforeClass
    public static void setUp() throws Exception {
        // 有没有更加优雅的方式
        DynamicDataSourceContextHolder.push("slave");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        DynamicDataSourceContextHolder.poll();
    }

    @Test
    public void test() {
        userService.save(new User(5, "binarylei5"));
        Assert.assertEquals("binarylei5", userService.getById(5L).getName());
    }

    @Test
    @Sql(statements = "insert into user values(3, 'binarylei3');"/*,
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD*//*,
            config = @SqlConfig(dataSource = "master", transactionManager = "masterTransactionManager")*/)
    public void testTx() {
        Assert.assertEquals("binarylei3", userService.getById(3).getName());
    }

    @Test
    public void testTxNotExists() {
        Assert.assertNull(userService.getById(3));
    }

}
