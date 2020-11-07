package com.github.binarylei.redis.local.core;

import com.github.binarylei.redis.local.LocalRedisConnection;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;

import java.util.List;
import java.util.Map;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class LocalRedisStringCommands extends AbstractRedisCommands implements RedisStringCommands {
    public LocalRedisStringCommands(LocalRedisConnection redisConnection) {
        super(redisConnection);
    }

    @Override
    public byte[] get(byte[] key) {
        return getDb().getString(key);
    }

    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        return new byte[0];
    }

    @Override
    public List<byte[]> mGet(byte[]... keys) {
        return null;
    }

    @Override
    public Boolean set(byte[] key, byte[] value) {
        getDb().put(key, value);
        return true;
    }

    @Override
    public Boolean set(byte[] key, byte[] value, Expiration expiration, SetOption option) {
        return null;
    }

    @Override
    public Boolean setNX(byte[] key, byte[] value) {
        return null;
    }

    @Override
    public Boolean setEx(byte[] key, long seconds, byte[] value) {
        return null;
    }

    @Override
    public Boolean pSetEx(byte[] key, long milliseconds, byte[] value) {
        return null;
    }

    @Override
    public Boolean mSet(Map<byte[], byte[]> tuple) {
        return null;
    }

    @Override
    public Boolean mSetNX(Map<byte[], byte[]> tuple) {
        return null;
    }

    @Override
    public Long incr(byte[] key) {
        return null;
    }

    @Override
    public Long incrBy(byte[] key, long value) {
        return null;
    }

    @Override
    public Double incrBy(byte[] key, double value) {
        return null;
    }

    @Override
    public Long decr(byte[] key) {
        return null;
    }

    @Override
    public Long decrBy(byte[] key, long value) {
        return null;
    }

    @Override
    public Long append(byte[] key, byte[] value) {
        return null;
    }

    @Override
    public byte[] getRange(byte[] key, long start, long end) {
        return new byte[0];
    }

    @Override
    public void setRange(byte[] key, byte[] value, long offset) {

    }

    @Override
    public Boolean getBit(byte[] key, long offset) {
        return null;
    }

    @Override
    public Boolean setBit(byte[] key, long offset, boolean value) {
        return null;
    }

    @Override
    public Long bitCount(byte[] key) {
        return null;
    }

    @Override
    public Long bitCount(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public List<Long> bitField(byte[] key, BitFieldSubCommands subCommands) {
        return null;
    }

    @Override
    public Long bitOp(BitOperation op, byte[] destination, byte[]... keys) {
        return null;
    }

    @Override
    public Long bitPos(byte[] key, boolean bit, Range<Long> range) {
        return null;
    }

    @Override
    public Long strLen(byte[] key) {
        return null;
    }
}
