package com.yangxj.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * @author yangxj
 * @date 2019-11-29 09:37
 */
@Component
@Aspect
@Priority(2)
public class DynamicDataSoureceAspect2 {
    @Pointcut("@within(com.yangxj.annotation.TargetDataSource)")
    public void cut(){}

    @Before("cut()")
    public void before(){
        System.out.println("DynamicDataSoureceAspect2 aop  do ......");
    }

}
