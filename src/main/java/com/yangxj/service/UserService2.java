package com.yangxj.service;

import com.yangxj.annotation.TargetDataSource;
import com.yangxj.entity.User;
import com.yangxj.mapper1.UserMapper1;
import com.yangxj.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;

/**
 * @author yangxj
 * @date 2019/5/21-20:43
 */
//@Transactional(transactionManager = "transactionManager2")
@Service
@TargetDataSource("ds2")
public class UserService2 {
    @Autowired
    UserMapper2 userMapper2;
    @TargetDataSource
    public void add(){
        User user = new User();
        user.setName("ds2");
        user.setMoney(200);
//        userMapper2.save(user);
    }
}
