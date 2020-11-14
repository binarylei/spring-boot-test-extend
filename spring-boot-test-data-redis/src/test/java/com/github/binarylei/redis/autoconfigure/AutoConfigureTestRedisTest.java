package com.github.binarylei.redis.autoconfigure;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author binarylei
 * @version 2020-11-08
 */
@AutoConfigureTestRedis
@SpringBootTest(classes = AutoConfigureTestRedisTest.class)
@RunWith(SpringRunner.class)
public class AutoConfigureTestRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testAutoConfigureTestRedis() {
        redisTemplate.opsForValue().set("key1", "value1");
        Assert.assertEquals("value1", redisTemplate.opsForValue().get("key1"));
    }

}
