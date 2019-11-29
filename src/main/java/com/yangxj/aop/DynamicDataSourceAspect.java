package com.yangxj.aop;

import com.yangxj.annotation.TargetDataSource;
import com.yangxj.config.DataSourceContextHolder;
import com.yangxj.config.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yangxj
 * @date 2019-11-28 19:33
 */
@Aspect
@Component
@Priority(1)
public class DynamicDataSourceAspect {
    public DynamicDataSourceAspect(){
        System.out.println("dataSourceAspect init ");
    }

    @Pointcut("@within(com.yangxj.annotation.TargetDataSource)")
    public void cut(){}

    @Before("cut()")
    public void switchDataSource(JoinPoint joinPoint) throws Exception {
        System.out.println("DynamicDataSoureceAspect aop  do ......");

//        String dataSource;
//        String methodName = joinPoint.getSignature().getName();
//
//        Class className = joinPoint.getSignature().getDeclaringType();
//
//        Method method = className.getMethod(methodName);
//
//        if(method.getAnnotation(TargetDataSource.class) != null){
//            dataSource = method.getAnnotation(TargetDataSource.class).value();
//        }else {
//            TargetDataSource annotation = (TargetDataSource) className.getAnnotation(TargetDataSource.class);
//            dataSource = annotation.value();
//        }
//        System.out.println("---当前使用数据源: " + dataSource);
//        DataSourceContextHolder.put(dataSource);
    }

    @After("cut()")
    public void afterSwitchDataSource(){
//        DataSourceContextHolder.clear();
    }
}
