package com.yangxj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangxj
 * @date 2019/5/20-21:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class
User_  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name="money")
    private Double money;
    @Column(columnDefinition = "date")
    private Date birthday;
    @Column(name = "create_time",columnDefinition = "dateTime")
    private Date createTime;
    @JsonIgnore
    public Specification<User_>  getSpecification(){
        return (Specification<User_>) (root, query, cb) -> {
            ArrayList<Predicate> predicates = Lists.newArrayList();
            if(!StringUtils.isEmpty(id)){
                predicates.add(cb.equal(root.get("id").as(Integer.class),id));
            }
            if(!StringUtils.isEmpty(name)){
                predicates.add(cb.like(root.get("name").as(String.class),name+"%"));
            }
            if(!StringUtils.isEmpty(money)){
                predicates.add(cb.equal(root.get("money").as(String.class),money));
            }
            if(birthday!=null){
                predicates.add(cb.equal(root.get("birthday").as(Date.class),birthday));
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(birthday);
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                System.out.println(format.format(birthday));
//                calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),23,59,59);
//                System.out.println("format.format(calendar.getTime()) = " + format.format(calendar.getTime()));
//                predicates.add(cb.lessThan(root.get("birthday").as(Date.class),calendar.getTime()));
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        };
    }

    public Date getBirthday2() {
        return birthday;
    }
    @JsonIgnore
    public Sort getSort(){
        return Sort.by(Sort.Order.desc("birthday"));
    }
    public String getNickName(){
        return "yangxj____nick";
    }
}
