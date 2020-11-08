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
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        // 删除单个
        Assert.assertEquals(true, redisTemplate.delete("key1"));
        Assert.assertEquals(false, redisTemplate.delete("key1"));

        // 批量删除
        Assert.assertEquals(1, redisTemplate.delete(
                Arrays.asList("key1", "key2")).longValue());
        Assert.assertEquals(0, redisTemplate.delete(
                Collections.emptyList()).longValue());
    }

    @Test
    public void keys() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        Assert.assertEquals(2, redisTemplate.keys("*").size());
        Assert.assertEquals(2, redisTemplate.keys("k*").size());
        Assert.assertEquals(1, redisTemplate.keys("key1").size());
        Assert.assertEquals(0, redisTemplate.keys("xxx").size());
    }

    @Test
    public void rename() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        Assert.assertEquals("value1", redisTemplate.opsForValue().get("key1"));
        Assert.assertEquals("value2", redisTemplate.opsForValue().get("key2"));
        redisTemplate.rename("key1", "key2");
        Assert.assertNull(redisTemplate.opsForValue().get("key1"));
        Assert.assertEquals("value1", redisTemplate.opsForValue().get("key2"));

        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        redisTemplate.renameIfAbsent("key1", "key2");
        Assert.assertEquals("value2", redisTemplate.opsForValue().get("key2"));
    }

}