package com.yangxj.common;

import com.yangxj.mapper2.UserMapper2;
import com.yangxj.service.UserService;
import com.yangxj.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangxj
 * @date 2019-11-28 20:39
 */
@Component
public class TestDynamicDataSource {
    @Autowired
    UserService userService;
    @Autowired
    UserService2 userService2;

    public void addAll(){
        userService.add();
        userService2.add();
    }
}
