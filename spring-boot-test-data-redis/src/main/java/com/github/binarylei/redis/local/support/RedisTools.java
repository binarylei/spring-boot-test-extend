package com.github.binarylei.redis.local.support;

import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public abstract class RedisTools {

    public static String byteToString(byte[] bytes) {
        return new StringRedisSerializer().deserialize(bytes);
    }

}
