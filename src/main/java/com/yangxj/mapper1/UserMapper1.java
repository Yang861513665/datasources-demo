package com.yangxj.mapper1;

import com.yangxj.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author yangxj
 * @date 2019/5/20-21:46
 */
public interface UserMapper1 {
    int save(User user);
    @Update("update redis_count set count = count -1")
    int countDown();

    @Select("select count from redis_count")
    int selectCount();
}
