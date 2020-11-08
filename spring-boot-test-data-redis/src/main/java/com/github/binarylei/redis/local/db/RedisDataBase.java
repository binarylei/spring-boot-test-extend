package com.github.binarylei.redis.local.db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.github.binarylei.redis.local.support.RedisTools.byteToString;

/**
 * 被动触发value过期
 *
 * @author binarylei
 * @version 2020-11-07
 */
public class RedisDataBase {

    /**
     * Redis 默认有 16 个 db
     */
    @Getter
    @Setter
    private RedisDataSet dataSet;

    /**
     * 支持最基础的五种数据类型的操作：String、List、Set、SortedSet、HashMap
     */
    @Getter
    private Map<String, Entry> data = new HashMap<>();

    public void put(byte[] key, Object value) {
        put(key, value, Long.MAX_VALUE);
    }

    @SuppressWarnings("unchecked")
    public void put(byte[] key, Object value, Long expire) {
        String keyStr = byteToString(key);
        data.put(byteToString(key), new Entry(keyStr, value, expire));
    }

    public Boolean expire(byte[] key, Long expire) {
        Entry entry = data.get(byteToString(key));
        if (entry != null) {
            return entry.expire(expire);
        }
        return null;
    }

    public Boolean expireAt(byte[] key, Long unixTimeInMillis) {
        Entry entry = data.get(byteToString(key));
        if (entry != null) {
            return entry.expireAt(unixTimeInMillis);
        }
        return null;
    }

    public Entry getEntry(byte[] key) {
        return data.get(byteToString(key));
    }

    public Entry putEntry(byte[] key, Entry entry) {
        return data.put(byteToString(key), entry);
    }

    public Object getValue(byte[] key) {
        return getValueFromEntry(getEntry(key));
    }

    public boolean isExpire(byte[] key) {
        Entry entry = getEntry(key);
        if (entry != null)
            return entry.isExpire();
        return true;
    }

    public boolean exists(byte[] key) {
        return data.containsKey(byteToString(key));
    }

    public boolean del(byte[] key) {
        return data.remove(byteToString(key)) != null;
    }

    public byte[] getString(byte[] key) {
        Entry entry = getEntry(key);
        return (byte[]) getValueFromEntry(entry);
    }

    @SuppressWarnings("unchecked")
    public List<byte[]> getList(byte[] key) {
        Entry entry = getEntry(key);
        return (List<byte[]>) getValueFromEntry(entry);
    }

    @SuppressWarnings("unchecked")
    public Map<String, byte[]> getMap(byte[] key) {
        Entry entry = getEntry(key);
        return (Map<String, byte[]>) getValueFromEntry(entry);
    }

    @SuppressWarnings("unchecked")
    public Set<byte[]> getSet(byte[] key) {
        Entry entry = getEntry(key);
        return (Set<byte[]>) getValueFromEntry(entry);
    }

    @SuppressWarnings("unchecked")
    public TreeSet<byte[]> getTreeSet(byte[] key) {
        Entry entry = getEntry(key);
        return (TreeSet<byte[]>) getValueFromEntry(entry);
    }

    /**
     * 如果 value 已经过期，直接删除 value，并返回 null
     *
     * @param entry value
     * @return 如果过期，则返回 null
     */
    private Object getValueFromEntry(Entry entry) {
        if (entry == null) return null;
        if (entry.isExpire()) {
            data.remove(entry.getKey());
            return null;
        }
        return entry.getValue();
    }

    public void flushDb() {
        data.clear();
    }

    @Data
    public static class Entry<T> {
        private String key;
        private T value;
        private long timestamp = Long.MAX_VALUE;
        private long ttl;

        public Entry(String key, T value, long expire) {
            this.key = key;
            this.value = value;

            expire(expire);
        }

        public boolean expire(long expire) {
            long timestamp = (expire == Long.MAX_VALUE || expire < 0) ?
                    Long.MAX_VALUE : System.currentTimeMillis() + expire;

            return expireAt(timestamp);
        }

        public boolean expireAt(long unixTime) {
            if (isExpire())
                return false;
            this.timestamp = unixTime;
            return true;
        }

        public boolean isExpire() {
            return System.currentTimeMillis() > timestamp;
        }

        public T getValue() {
            return !isExpire() ? value : null;
        }

    }

}
