package com.github.binarylei.redis.local.core;

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

    }

    @Override
    public void bgSave() {

    }

    @Override
    public Long lastSave() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public Long dbSize() {
        return null;
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

    }

    @Override
    public void shutdown(ShutdownOption option) {

    }

    @Override
    public Properties getConfig(String pattern) {
        return null;
    }

    @Override
    public void setConfig(String param, String value) {

    }

    @Override
    public void resetConfigStats() {

    }

    @Override
    public Long time() {
        return null;
    }

    @Override
    public void killClient(String host, int port) {

    }

    @Override
    public void setClientName(byte[] name) {

    }

    @Override
    public String getClientName() {
        return null;
    }

    @Override
    public List<RedisClientInfo> getClientList() {
        return null;
    }

    @Override
    public void slaveOf(String host, int port) {

    }

    @Override
    public void slaveOfNoOne() {

    }

    @Override
    public void migrate(byte[] key, RedisNode target, int dbIndex, MigrateOption option) {

    }

    @Override
    public void migrate(byte[] key, RedisNode target, int dbIndex, MigrateOption option, long timeout) {

    }
}
