package com.yangxj.service;

import com.yangxj.entity.User;
import com.yangxj.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxj
 * @date 2019/5/21-20:43
 */
@Service
@Transactional(transactionManager = "transactionManager2")
public class UserService2 {
    @Autowired
    UserMapper2 userMapper2;
    public void add(){
        userMapper2.save(new User("yangxj2","50"));
        int i = 10/0;
    }
}
