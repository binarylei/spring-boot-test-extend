package com.github.binarylei.redis.local;

import com.github.binarylei.redis.local.command.LocalRedisKeyCommands;
import com.github.binarylei.redis.local.command.LocalRedisServerCommands;
import com.github.binarylei.redis.local.command.LocalRedisStringCommands;
import com.github.binarylei.redis.local.db.RedisDataBase;
import com.github.binarylei.redis.local.db.RedisDataSet;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DefaultedRedisConnection;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.RedisPipelineException;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.Subscription;

import java.util.List;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class LocalRedisConnection implements DefaultedRedisConnection {

    private int dbIndex = 0;
    private RedisDataSet dataSet;

    public LocalRedisConnection(RedisDataSet dataSet) {
        this.dataSet = dataSet;
    }

    public RedisDataBase getDb() {
        return this.dataSet.select(dbIndex);
    }

    @Override
    public RedisKeyCommands keyCommands() {
        return new LocalRedisKeyCommands(this);
    }

    @Override
    public RedisServerCommands serverCommands() {
        return new LocalRedisServerCommands(this);
    }

    @Override
    public RedisStringCommands stringCommands() {
        return new LocalRedisStringCommands(this);
    }

    @Override
    public void close() throws DataAccessException {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public Object getNativeConnection() {
        return null;
    }

    @Override
    public boolean isQueueing() {
        return false;
    }

    @Override
    public boolean isPipelined() {
        return false;
    }

    @Override
    public void openPipeline() {

    }

    @Override
    public List<Object> closePipeline() throws RedisPipelineException {
        return null;
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return null;
    }

    @Override
    public Object execute(String command, byte[]... args) {
        return null;
    }

    @Override
    public void select(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    @Override
    public byte[] echo(byte[] message) {
        return new byte[0];
    }

    @Override
    public String ping() {
        return null;
    }

    @Override
    public boolean isSubscribed() {
        return false;
    }

    @Override
    public Subscription getSubscription() {
        return null;
    }

    @Override
    public Long publish(byte[] channel, byte[] message) {
        return null;
    }

    @Override
    public void subscribe(MessageListener listener, byte[]... channels) {

    }

    @Override
    public void pSubscribe(MessageListener listener, byte[]... patterns) {

    }

    @Override
    public void multi() {

    }

    @Override
    public List<Object> exec() {
        return null;
    }

    @Override
    public void discard() {

    }

    @Override
    public void watch(byte[]... keys) {

    }

    @Override
    public void unwatch() {

    }
}
