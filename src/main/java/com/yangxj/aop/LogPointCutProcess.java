//package com.yangxj.aop;
//
//import com.google.common.base.Stopwatch;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author yangxj
// * @date 2019/7/28-11:29
// */
//@Component
//@Aspect
//@Slf4j
//public class LogPointCutProcess {
//
//    @Around(value = "pointcut()")
//    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        Object retVal = pjp.proceed();
//        log.info("方法: {},耗时: {}ms",pjp.getSignature().toString(),stopwatch.elapsed(TimeUnit.MILLISECONDS));
//        return retVal;
//    }
//    @Pointcut("execution(* com.yangxj.controller.*.*(..))")
//    public void pointcut(){
//    }
//}
