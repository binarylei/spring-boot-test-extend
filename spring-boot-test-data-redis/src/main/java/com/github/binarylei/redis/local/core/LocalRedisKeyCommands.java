package com.github.binarylei.redis.local.core;

import com.github.binarylei.redis.local.LocalRedisConnection;
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
public class LocalRedisKeyCommands extends AbstractRedisCommands implements RedisKeyCommands {

    public LocalRedisKeyCommands(LocalRedisConnection redisConnection) {
        super(redisConnection);
    }

    @Override
    public Long exists(byte[]... keys) {
        return null;
    }

    @Override
    public Long del(byte[]... keys) {
        return null;
    }

    @Override
    public Long unlink(byte[]... keys) {
        return null;
    }

    @Override
    public DataType type(byte[] key) {
        return null;
    }

    @Override
    public Long touch(byte[]... keys) {
        return null;
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        return null;
    }

    @Override
    public Cursor<byte[]> scan(ScanOptions options) {
        return null;
    }

    @Override
    public byte[] randomKey() {
        return new byte[0];
    }

    @Override
    public void rename(byte[] sourceKey, byte[] targetKey) {

    }

    @Override
    public Boolean renameNX(byte[] sourceKey, byte[] targetKey) {
        return null;
    }

    @Override
    public Boolean expire(byte[] key, long seconds) {
        return null;
    }

    @Override
    public Boolean pExpire(byte[] key, long millis) {
        return null;
    }

    @Override
    public Boolean expireAt(byte[] key, long unixTime) {
        return null;
    }

    @Override
    public Boolean pExpireAt(byte[] key, long unixTimeInMillis) {
        return null;
    }

    @Override
    public Boolean persist(byte[] key) {
        return null;
    }

    @Override
    public Boolean move(byte[] key, int dbIndex) {
        return null;
    }

    @Override
    public Long ttl(byte[] key) {
        return null;
    }

    @Override
    public Long ttl(byte[] key, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Long pTtl(byte[] key) {
        return null;
    }

    @Override
    public Long pTtl(byte[] key, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public List<byte[]> sort(byte[] key, SortParameters params) {
        return null;
    }

    @Override
    public Long sort(byte[] key, SortParameters params, byte[] storeKey) {
        return null;
    }

    @Override
    public byte[] dump(byte[] key) {
        return new byte[0];
    }

    @Override
    public void restore(byte[] key, long ttlInMillis, byte[] serializedValue, boolean replace) {

    }

    @Override
    public ValueEncoding encodingOf(byte[] key) {
        return null;
    }

    @Override
    public Duration idletime(byte[] key) {
        return null;
    }

    @Override
    public Long refcount(byte[] key) {
        return null;
    }
}
