//package com.yangxj.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Lists;
//import com.yangxj.cache.UserCacheConfig;
//import com.yangxj.entity.User_;
//import com.yangxj.mapper1.UserMapper1;
//import com.yangxj.repository.UserRepository;
//import com.yangxj.util.RedisLockUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//import java.util.concurrent.*;
//
///**
// * @author yangxj
// * @date 2019/5/23-21:55
// */
//@RestController
//public class TestController {
//    @Autowired
//    RestTemplate restTemplate;
//    @Autowired
//    UserCacheConfig userCacheConfig;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserMapper1 userMapper1;
//    @Autowired
//    RedisLockUtil redisLockUtil;
//    @RequestMapping("test")
//    public Object test(){
//        Calendar instance = Calendar.getInstance();
//        instance.setTime(new Date());
//        User_  yangxj01 = User_.builder().name("yangxj01").money(200.0).birthday(instance.getTime()).build();
//        instance.add(Calendar.DATE,-1);
//        User_  yangxj02 = User_.builder().name("yangxj02").money(200.0).birthday(instance.getTime()).build();
//        instance.add(Calendar.HOUR,1);
//        User_  yangxj03 = User_.builder().name("yangxj03").money(200.0).birthday(instance.getTime()).build();
//        List<User_> user_s = userRepository.saveAll(Lists.newArrayList(yangxj01, yangxj02, yangxj03));
//        return user_s;
//    }
//
//    @RequestMapping("test2")
//    public Object test2(String name,int age){
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("name",name);
//        result.put("age",age);
//        String object = restTemplate.getForObject("http://localhost:8080/test?msg={result}" , String.class,JSONObject.toJSONString(result));
//        System.out.println(object);
//        return object;
//    }
//    @RequestMapping("find")
//    public Object find(User_ user){
//        return userRepository.findAll(user.getSpecification(),user.getSort());
//    }
//    @RequestMapping("update")
//    public Object update(){
//        User_  yangxj01 = User_.builder().name("yangxj01").money(200.0).id(7).build();
//        System.out.println(yangxj01.getBirthday());
//       return userRepository.save(yangxj01);
//    }
//    @RequestMapping("add")
//    public Object add(User_ user){
//        System.out.println("add user==>"+user);
//        return userRepository.save(user);
//    }
//    @RequestMapping("testCache")
//    public User_ getUserById(String id) throws ExecutionException {
//        return userCacheConfig.getUserById(id);
//    }
//    @RequestMapping("redis")
//    public void test22(){
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Random random = new Random();
//        CountDownLatch countDownLatch = new CountDownLatch(32);
//        for (int i = 0; i < 32; i++) {
//            executorService.submit(() -> {
//                try {
//                    System.out.println("准备执行。。。");
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//               try {
//                   redisLockUtil.lock("count");
//                   int count = userMapper1.selectCount();
//                   if(count>0){
//                       System.out.println("执行扣减。。。"+userMapper1.countDown());
//                   }
//                   redisLockUtil.unlock("count");
//                   Thread.sleep(random.nextInt(100));
//               }catch (Exception e){
//                   e.printStackTrace();
//               }
//            });
//            countDownLatch.countDown();
//          }
//        executorService.shutdown();
//    }
//}
