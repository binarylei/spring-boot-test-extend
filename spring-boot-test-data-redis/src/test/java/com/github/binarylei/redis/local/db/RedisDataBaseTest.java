package com.github.binarylei.redis.local.db;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binarylei
 * @version 2020-11-08
 */
public class RedisDataBaseTest {


    private RedisDataSet dataSet = new RedisDataSet();
    private RedisDataBase dataBase = dataSet.select(0);

    @After
    public void tearDown() throws Exception {
        dataBase.flushDb();
    }

    @Test
    public void testString() {
        dataBase.put("key1".getBytes(), "value1".getBytes());
        Assert.assertArrayEquals("value1".getBytes(), dataBase.getString("key1".getBytes()));
    }

    @Test
    public void testKeyExpire() throws InterruptedException {
        dataBase.put("key1".getBytes(), "value1".getBytes(), 10L);
        Assert.assertFalse(dataBase.isExpire("key1".getBytes()));
        Thread.sleep(20);
        Assert.assertTrue(dataBase.isExpire("key1".getBytes()));
    }

}