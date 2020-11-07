package com.github.binarylei.redis.local.core;

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
 * @author binarylei
 * @version 2020-11-07
 */
public class RedisDataBase {

    @Getter
    @Setter
    private RedisDataSet dataSet;
    @Getter
    private Map<String, Object> data = new HashMap<>();

    public void put(byte[] key, Object value) {
        data.put(byteToString(key), value);
    }

    public byte[] getString(byte[] key) {
        return (byte[]) data.get(byteToString(key));
    }

    @SuppressWarnings("unchecked")
    public List<byte[]> getList(byte[] key) {
        return (List<byte[]>) data.get(byteToString(key));
    }

    @SuppressWarnings("unchecked")
    public Map<String, byte[]> getMap(byte[] key) {
        return (Map<String, byte[]>) data.get(byteToString(key));
    }

    @SuppressWarnings("unchecked")
    public Set<byte[]> getSet(byte[] key) {
        return (Set<byte[]>) data.get(byteToString(key));
    }

    @SuppressWarnings("unchecked")
    public TreeSet<byte[]> getTreeSet(byte[] key) {
        return (TreeSet<byte[]>) data.get(byteToString(key));
    }

    public byte[] getValue(Entry entry) {
        return entry != null ? entry.getValue() : null;
    }

    @Data
    public static class Entry {
        private byte[] value;
        private long timestamp;

        public Entry(byte[] value) {
            this(value, Long.MAX_VALUE);
        }

        public Entry(byte[] value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        public byte[] getValue() {
            return timestamp > System.currentTimeMillis() ? value : null;
        }

    }

}
