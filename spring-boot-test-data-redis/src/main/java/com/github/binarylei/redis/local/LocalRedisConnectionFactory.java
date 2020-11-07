package com.github.binarylei.redis.local;

import com.github.binarylei.redis.local.core.RedisDataSet;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class LocalRedisConnectionFactory implements RedisConnectionFactory {

    private LocalRedisConnection redisConnection = new LocalRedisConnection(new RedisDataSet());

    @Override
    public RedisConnection getConnection() {
        return redisConnection;
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return null;
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return false;
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return null;
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException e) {
        return null;
    }
}
