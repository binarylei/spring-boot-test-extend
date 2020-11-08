package com.github.binarylei.redis.local.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author binarylei
 * @version 2020-11-08
 */
public class LocalRedisKeyCommandsTest extends AbstractLocalRedisCommandsTest {

    @Test
    public void exists() {
        Assert.assertEquals(0, redisTemplate.countExistingKeys(
                Collections.emptyList()).longValue());
        Assert.assertEquals(0, redisTemplate.countExistingKeys(
                Arrays.asList("key1", "key2", "key3")).longValue());

        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        Assert.assertEquals(2, redisTemplate.countExistingKeys(
                Arrays.asList("key1", "key2", "key3")).longValue());
    }

    @Test
    public void del() {

    }

}