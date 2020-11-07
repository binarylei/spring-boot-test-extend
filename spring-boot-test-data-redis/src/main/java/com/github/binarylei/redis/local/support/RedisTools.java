package com.github.binarylei.redis.local.support;

import com.github.binarylei.redis.local.db.RedisDataBase;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public abstract class RedisTools {

    public static String byteToString(byte[] bytes) {
        return new StringRedisSerializer().deserialize(bytes);
    }

    public static byte[] getValue(RedisDataBase.Entry entry) {
        return entry != null ? entry.getValue() : null;
    }
}
