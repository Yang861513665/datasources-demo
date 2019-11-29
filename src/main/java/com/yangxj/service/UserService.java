package com.yangxj.service;

import com.yangxj.annotation.TargetDataSource;
import com.yangxj.entity.User;
import com.yangxj.mapper1.UserMapper1;
import com.yangxj.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;

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

//        userMapper1.save(user);
    }
}
