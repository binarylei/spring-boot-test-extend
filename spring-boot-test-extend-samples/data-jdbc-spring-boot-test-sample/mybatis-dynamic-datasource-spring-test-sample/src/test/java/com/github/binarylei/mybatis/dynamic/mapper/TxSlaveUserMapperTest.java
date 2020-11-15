package com.github.binarylei.mybatis.dynamic.mapper;

import com.github.binarylei.mybatis.dynamic.SpringBootTestFramework;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author binarylei
 * @version 2020-11-15
 */
@Transactional(transactionManager = "slaveTransactionManager")
public class TxSlaveUserMapperTest extends SpringBootTestFramework {

    @Autowired
    private SlaveUserMapper slaveUserMapper;

    @Test
    @Sql(statements = "insert into user values(4, 'binarylei4');",
            config = @SqlConfig(dataSource = "slave", transactionManager = "slaveTransactionManager"))
    public void testTx() {
        Assert.assertEquals("binarylei4", slaveUserMapper.selectUserById(4L).getName());
    }

    @Test
    public void testNotExist() {
        Assert.assertNull(slaveUserMapper.selectUserById(4L));
    }

}
