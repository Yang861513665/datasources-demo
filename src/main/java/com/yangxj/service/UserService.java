package com.yangxj.service;

import com.yangxj.entity.User;
import com.yangxj.mapper1.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxj
 * @date 2019/5/20-23:01
 */
@Service
@Transactional(transactionManager = "transactionManager1")
public class UserService {
    @Autowired
    UserMapper1 userMapper1;
    @Autowired
    UserService2 userService2;
    public void add(){
        userMapper1.save(new User("yangxj1","50"));
        userService2.add();
    }
}
