package com.yangxj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangxj
 * @date 2019-11-29 14:14
 */
public class DateUtils {
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String formate(Date date){
        return dateFormat.format(date);
    }
    public static Date parse(String date){
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String name= "yangxj123";
        System.out.println(name.substring(0,name.indexOf(".")));
        System.out.println(name.substring(name.indexOf(".")+1));
    }
}
