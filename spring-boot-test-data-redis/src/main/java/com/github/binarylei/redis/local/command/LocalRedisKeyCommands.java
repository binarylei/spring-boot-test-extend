package com.github.binarylei.redis.local.command;

import com.github.binarylei.redis.local.LocalRedisConnection;
import com.github.binarylei.redis.local.db.RedisDataBase;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.connection.ValueEncoding;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.github.binarylei.redis.local.support.RedisTools.byteToString;

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
        if (keys.length == 0) return 0L;

        RedisDataBase db = getDb();
        return Arrays.stream(keys).filter(db::exists).count();
    }

    @Override
    public Long del(byte[]... keys) {
        if (keys.length == 0) return 0L;

        RedisDataBase db = getDb();
        // 返回删除元素的个数
        return Arrays.stream(keys).filter(db::del).count();
    }

    @Override
    public Long unlink(byte[]... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataType type(byte[] key) {
        Object value = getDb().getValue(key);
        if (value == null) {
            return DataType.NONE;
        } else if (value instanceof byte[]) {
            return DataType.STRING;
        } else if (value instanceof TreeSet) {
            return DataType.ZSET;
        } else if (value instanceof Set) {
            return DataType.SET;
        } else if (value instanceof Map) {
            return DataType.HASH;
        }
        return DataType.NONE;
    }

    @Override
    public Long touch(byte[]... keys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        Pattern compile = Pattern.compile(byteToString(pattern).replace("*", ".*"));
        return getDb().getData().keySet().stream().filter(key -> compile.matcher(key).find())
                .map(String::getBytes).collect(Collectors.toSet());
    }

    @Override
    public Cursor<byte[]> scan(ScanOptions options) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] randomKey() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rename(byte[] sourceKey, byte[] targetKey) {
        Map<String, RedisDataBase.Entry> data = getDb().getData();
        RedisDataBase.Entry entry = data.remove(byteToString(sourceKey));
        if (entry != null && !entry.isExpire()) {
            getDb().put(targetKey, entry.getValue(), entry.getExpire());
        }
    }

    @Override
    public Boolean renameNX(byte[] sourceKey, byte[] targetKey) {
        RedisDataBase db = getDb();
        if (db.exists(sourceKey) && !db.exists(targetKey)) {
            rename(sourceKey, targetKey);
            return true;
        }
        return false;
    }

    @Override
    public Boolean expire(byte[] key, long seconds) {
        return pExpire(key, TimeUnit.MILLISECONDS.toMillis(seconds));
    }

    /**
     * expire(seconds) 和 pExpire(millis) 最主要的区别的时间单例
     */
    @Override
    public Boolean pExpire(byte[] key, long millis) {
        return getDb().expire(key, millis);
    }

    @Override
    public Boolean expireAt(byte[] key, long unixTime) {
        return getDb().expireAt(key, TimeUnit.MILLISECONDS.toMillis(unixTime));
    }

    @Override
    public Boolean pExpireAt(byte[] key, long unixTimeInMillis) {
        return getDb().expireAt(key, unixTimeInMillis);
    }

    @Override
    public Boolean persist(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean move(byte[] key, int dbIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long ttl(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long ttl(byte[] key, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long pTtl(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long pTtl(byte[] key, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<byte[]> sort(byte[] key, SortParameters params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long sort(byte[] key, SortParameters params, byte[] storeKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] dump(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void restore(byte[] key, long ttlInMillis, byte[] serializedValue, boolean replace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueEncoding encodingOf(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duration idletime(byte[] key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long refcount(byte[] key) {
        throw new UnsupportedOperationException();
    }
}
