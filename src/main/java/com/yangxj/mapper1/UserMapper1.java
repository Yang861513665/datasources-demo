package com.yangxj.mapper1;

import com.yangxj.annotation.TargetDataSource;
import com.yangxj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author yangxj
 * @date 2019/5/20-21:46
 */
public interface UserMapper1 {
    int save(User user);

    void batchAdd(User user);

    void save2(String name, Integer money, Date birthday);

    void save3(User user);

//    @Update("update redis_count set count = count -1")
//    int countDown();
//
//    @Select("select count from redis_count")
//    int selectCount();
}
