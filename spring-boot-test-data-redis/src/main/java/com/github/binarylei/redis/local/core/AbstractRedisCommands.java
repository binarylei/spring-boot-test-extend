package com.github.binarylei.redis.local.core;

import com.github.binarylei.redis.local.LocalRedisConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.connection.ValueEncoding;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
