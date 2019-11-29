package com.yangxj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangxj
 * @date 2019/5/20-21:48
 */
@Data
public class User {
//    @Column
    private String name;
//    @Column
    private Integer money;
    private Date birthday;
    private User user;
    private List<User> users;

    private Map<String,Object> map;
    private Boolean status;

    public static void main(String[] args) {
        String sql = "insert into student values(?)";
        String name = "yangxj";
        sql = sql.replaceFirst("\\?", "'"+ name + "'" );
        System.out.println(sql);
    }
}
