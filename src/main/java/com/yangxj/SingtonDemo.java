package com.yangxj;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2019/7/25-22:10
 */
public class SingtonDemo implements Callable<String>{
    private static SingtonDemo singtonDemo;
    private SingtonDemo(){
    }
    //DCL 双端检索机制
    public SingtonDemo getSington(){
        if(singtonDemo==null){
            synchronized (SingtonDemo.class){
                if(singtonDemo==null){
                    singtonDemo = new SingtonDemo();
                }
            }
        }
        return singtonDemo;
    }

    public static void main(String[] args) throws Exception {

    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return "hello";
    }
}
