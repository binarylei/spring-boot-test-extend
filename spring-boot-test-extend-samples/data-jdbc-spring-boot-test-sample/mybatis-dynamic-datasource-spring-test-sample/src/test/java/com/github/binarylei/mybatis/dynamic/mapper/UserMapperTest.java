package com.github.binarylei.mybatis.dynamic.mapper;

import com.github.binarylei.mybatis.dynamic.SpringBootTestFramework;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author binarylei
 * @version 2020-11-15
 */
public class UserMapperTest extends SpringBootTestFramework {

    @Autowired
    private MasterUserMapper masterUserMapper;
    @Autowired
    private SlaveUserMapper slaveUserMapper;

    @Test
    public void test() {
        Assert.assertEquals("binarylei1", masterUserMapper.selectUserById(1L).getName());
        Assert.assertEquals("binarylei2", slaveUserMapper.selectUserById(2L).getName());
    }
}
