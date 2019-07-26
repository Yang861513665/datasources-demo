package com.yangxj.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yangxj.entity.User;
import com.yangxj.entity.User_;
import com.yangxj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2019/7/3-20:41
 */
@Component
@Slf4j
public class UserCacheConfig {
    private LoadingCache<Integer, List<User_>>  userCache;
    @Autowired
    UserRepository userRepository;

    public  UserCacheConfig(){
        log.info("userCacheConfig 无参构造调用....");
    }
    @PostConstruct
    public void init(){
        log.info("init.....");
        this.userCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, List<User_>>() {
                    @Override
                    public List<User_> load(Integer key) throws Exception {
                        System.out.println("从数据库中获取数据....."+key);
                        List<User_> users = userRepository.findAll();
                        return users;
                    }
                });
    }
    public User_ getUserById(String id) throws ExecutionException {
        List<User_> list = userCache.get(1);
        for (User_ user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
}}
