package com.yangxj.config;

/**
 * @author yangxj
 * @date 2019-11-28 19:30
 */
public class DataSourceContextHolder {
    public static ThreadLocal<String> context = new ThreadLocal<>();
    public static final String DEFAULT_DS = "ds1";

    public static String get(){
        return context.get();
    }
    public static void put(String dataSourceName){
        context.set(dataSourceName);
    }
    public static void clear(){
        context.remove();
    }
}
