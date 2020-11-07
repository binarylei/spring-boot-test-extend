package com.github.binarylei.redis.local.command;

import com.github.binarylei.redis.local.LocalRedisConnection;
import com.github.binarylei.redis.local.db.RedisDataBase;
import lombok.RequiredArgsConstructor;

/**
 * @author binarylei
 * @version 2020-11-07
 */
@RequiredArgsConstructor
public class AbstractRedisCommands {

    protected final LocalRedisConnection redisConnection;

    protected RedisDataBase getDb() {
        return redisConnection.getDb();
    }

}
