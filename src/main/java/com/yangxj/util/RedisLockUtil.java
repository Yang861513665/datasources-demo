package com.yangxj.util;


import com.yangxj.cache.RedisUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2019/7/27-0:31
 */
@Component
public class RedisLockUtil {
    private ThreadLocal<String> local = new ThreadLocal<>();


    public void lock(String lockName) throws Exception {
        //尝试加锁，获取不到就休眠100ms
       if (tryLock(lockName)){
           return;
       }else {
           TimeUnit.MILLISECONDS.sleep(100);
           lock(lockName);
       }
    }

    public boolean tryLock(String lockName) {
        String uuid = UUID.randomUUID().toString().replaceAll("_","");
        Jedis jedis = RedisUtils.getConnection();
        String result = jedis.set(lockName, uuid, "nx", "px", 200);
        jedis.close();
        if(result!=null && result.equals("OK")){
            local.set(uuid);
            return true;
        }
        return false;
    }

    public void unlock(String lockName) {
        String uuid = local.get();
        String lua = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";// lua脚本，用来释放分布式锁
        Jedis jedis = RedisUtils.getConnection();
//        Object eval = jedis.eval(lua, Collections.singletonList(lockName),Collections.singletonList(uuid));
        Object eval = jedis.eval(lua, Arrays.asList(lockName), Arrays.asList(uuid));
        if(eval.equals("1")){
            System.out.println("release lock success");
        }else{
            System.out.println("release lock fail");
        }
        jedis.close();
    }
}
