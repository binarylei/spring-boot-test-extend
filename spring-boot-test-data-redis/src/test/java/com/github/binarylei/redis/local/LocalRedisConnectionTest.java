package com.github.binarylei.redis.local;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class LocalRedisConnectionTest {

    private RedisTemplate<String, Object> redisTemplate;

    @Before
    public void before() {
        LocalRedisConnectionFactory redisConnectionFactory = new LocalRedisConnectionFactory();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    @Test
    public void test() {
        redisTemplate.opsForValue().set("key1", "value1");
        Assert.assertEquals("value1", redisTemplate.opsForValue().get("key1"));
    }

}
