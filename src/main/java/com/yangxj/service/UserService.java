package com.yangxj.service;

import com.google.common.collect.Lists;
import com.yangxj.annotation.TargetDataSource;
import com.yangxj.entity.User;
import com.yangxj.mapper1.UserMapper1;
import com.yangxj.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author yangxj
 * @date 2019/5/20-23:01
 */
@Service
@TargetDataSource
public class UserService {
    @Autowired
    UserMapper1 userMapper1;
    public void add(){
        User user = new User();
        user.setName("ds1");
        user.setMoney(200);
        user.setBirthday(new Date());
        User user1 = new User();
        user1.setUser(user);
        userMapper1.save(user1);
    }
    public void batchAdd(){
        User user1 = new User();
        user1.setName("yangxj-01");

        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setName("yangxj-02");

        user2.setBirthday(new Date());

        ArrayList<User> users = Lists.newArrayList(user1, user2);

        User user = new User();
        user.setUsers(users);
        user.setMoney(200);

        userMapper1.batchAdd(user);
    }
    public void add2(){
        User user = new User();
        user.setName("ds1");
        user.setBirthday(new Date());
        userMapper1.save2(user.getName(),user.getMoney(),user.getBirthday());
    }
    public void add3(){
        User user = new User();
        user.setBirthday(new Date());
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","yangxj01");
        map.put("money",200);
        user.setStatus(true);
        user.setMap(map);
        userMapper1.save3(user);
    }
}
