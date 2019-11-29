//package com.yangxj.repository;
//
//import com.yangxj.entity.User_;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.Temporal;
//import java.util.List;
//
///**
// * @author yangxj
// * @date 2019/7/3-23:11
// */
//public interface UserRepository extends JpaRepository<User_, Integer> , JpaSpecificationExecutor<User_> {
//    @Query("select count(id) from User_  where id <>?1")
//    public int findNotid(Integer id);
//    @Query(value = "select count(name) from User_  where  name =?1")
//   public int findByName(String name);
//
//    @Query(value = "select * from tb_user where name like ?1%  ORDER by birthday asc",nativeQuery = true)
//    public List<User_> findByNameLike(String name);
//}
