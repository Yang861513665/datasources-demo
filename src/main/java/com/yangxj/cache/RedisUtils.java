package com.yangxj.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yangxj
 * @date 2019/7/26-23:28
 */
public class RedisUtils {
    static JedisPool jedisPool;

    public static void init(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(poolConfig,"119.23.57.79",6379);
    }
    public static Jedis getConnection(){
        if(jedisPool==null){
            init();
        }
        return jedisPool.getResource();
    }
    public static void close(Jedis jedis){
         jedis.close();
        jedisPool.close();
    }
}
