package com.yangxj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangxj
 * @date 2019/5/20-21:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
//    @Column
    private String name;
//    @Column
    private Integer money;
//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(format.format(calendar.getTime()));
//        System.out.println(calendar.getMaximum(Calendar.SECOND));
//        System.out.println(calendar.getActualMaximum(Calendar.SECOND));
//        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),23,59,59);
//        System.out.println(format.format(calendar.getTime()));
//    }

}
