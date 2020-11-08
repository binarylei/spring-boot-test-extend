package com.github.binarylei.redis.local.command;

import com.github.binarylei.redis.local.LocalRedisConnection;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.types.RedisClientInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class LocalRedisServerCommands extends AbstractRedisCommands implements RedisServerCommands {
    public LocalRedisServerCommands(LocalRedisConnection redisConnection) {
        super(redisConnection);
    }

    @Override
    public void bgReWriteAof() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bgSave() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long lastSave() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long dbSize() {
        return (long) getDb().getData().size();
    }

    @Override
    public void flushDb() {
        getDb().getData().clear();
    }

    @Override
    public void flushAll() {
        Arrays.stream(getDb().getDataSet().selectAll()).forEach(
                dataBase -> dataBase.getData().clear());
    }

    @Override
    public Properties info() {
        return null;
    }

    @Override
    public Properties info(String section) {
        return null;
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown(ShutdownOption option) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Properties getConfig(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setConfig(String param, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetConfigStats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long time() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void killClient(String host, int port) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClientName(byte[] name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getClientName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RedisClientInfo> getClientList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void slaveOf(String host, int port) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void slaveOfNoOne() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void migrate(byte[] key, RedisNode target, int dbIndex, MigrateOption option) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void migrate(byte[] key, RedisNode target, int dbIndex, MigrateOption option, long timeout) {
        throw new UnsupportedOperationException();
    }
}
