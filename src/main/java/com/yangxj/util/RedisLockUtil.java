package com.yangxj.util;


import com.yangxj.cache.RedisUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2019/7/27-0:31
 */
public class RedisLockUtil {
    private ThreadLocal<String> local = new ThreadLocal<>();


    public void lock(String lockName) throws Exception {
        //尝试加锁，获取不到就休眠100ms
       while (!tryLock(lockName)){
           TimeUnit.MILLISECONDS.sleep(100);
       }
    }

    public boolean tryLock(String lockName) {
        String uuid = UUID.randomUUID().toString();
        Jedis jedis = RedisUtils.getConnection();
        String result = jedis.set(lockName, uuid, "nx", "px", 200);
        if(result!=null && result.equals("OK")){
            local.set(uuid);
            return true;
        }
        return false;
    }

    public void unlock(String lockName) {
        String uuid = local.get();
        String lua = "if redis.call('get',KEYS[1]==ARGV[1]) then return redis.call ('del',KEYS[1]) else return 0 end";
        Jedis jedis = RedisUtils.getConnection();
        jedis.eval(lua,1,lockName,uuid);
    }
}
