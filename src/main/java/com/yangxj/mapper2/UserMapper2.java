package com.yangxj.mapper2;

import com.yangxj.entity.User;
import org.apache.ibatis.annotations.Insert;

/**
 * @author yangxj
 * @date 2019/5/20-21:47
 */
import org.apache.ibatis.annotations.Mapper;
public interface UserMapper2 {
    int save(User user);
}
