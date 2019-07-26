package com.yangxj.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yangxj
 * @date 2019/5/20-21:13
 */
@Configuration
@MapperScan(basePackages = "com.yangxj.mapper1",sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1{

    @Bean(name = "ds1")
    @Primary
    DataSource dataSource1(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://119.23.57.79:3306/ds1?characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }
    @Bean(name = "sqlSessionFactory1")
    @Primary
    SqlSessionFactory sqlSessionFactory1(@Qualifier("ds1")DataSource dataSource) throws Exception {
       final SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mapper1/*.xml"));
        return factory.getObject();
    }
    @Bean
    @Primary
    DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource1());
    }
//    @Bean(name = "sqlSessionTemplate1")
//    @Primary
//    SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
