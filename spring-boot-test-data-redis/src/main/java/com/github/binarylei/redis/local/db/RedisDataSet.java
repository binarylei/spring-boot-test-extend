package com.github.binarylei.redis.local.db;

/**
 * @author binarylei
 * @version 2020-11-07
 */
public class RedisDataSet {

    private int dbSize;
    private RedisDataBase[] dbs;

    public RedisDataSet() {
        this(16);
    }

    public RedisDataSet(int dbSize) {
        this.dbSize = dbSize;
        this.dbs = new RedisDataBase[dbSize];
        for (int i = 0; i < dbs.length; i++) {
            dbs[i] = new RedisDataBase();
            dbs[i].setDataSet(this);
        }
    }

    public RedisDataBase select(int dbIndex) {
        return dbs[dbIndex];
    }

    public RedisDataBase[] selectAll() {
        return dbs;
    }

}
