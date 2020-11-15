package com.github.binarylei.mybatis.dynamic.mapper;

import com.github.binarylei.mybatis.dynamic.SpringBootTestFramework;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author binarylei
 * @version 2020-11-15
 */
@Transactional
public class TxMasterUserMapperTest extends SpringBootTestFramework {

    @Autowired
    private MasterUserMapper masterUserMapper;

    @Test
    @Sql(statements = "insert into user values(3, 'binarylei3');")
    public void testTx() {
        Assert.assertEquals("binarylei3", masterUserMapper.selectUserById(3L).getName());
    }

    @Test
    public void testNotExist() {
        Assert.assertNull(masterUserMapper.selectUserById(3L));
    }

}
