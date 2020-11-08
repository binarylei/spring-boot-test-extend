package com.github.binarylei.redis.local.command;

import com.github.binarylei.redis.local.LocalRedisConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author binarylei
 * @version 2020-11-08
 */
public abstract class AbstractLocalRedisCommandsTest {

    protected RedisTemplate<String, Object> redisTemplate;

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

    @After
    public void tearDown() throws Exception {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}