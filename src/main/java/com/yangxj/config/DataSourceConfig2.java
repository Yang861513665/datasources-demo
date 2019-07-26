package com.yangxj.config;

import com.sun.corba.se.pept.transport.TransportManager;
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
@MapperScan(basePackages = "com.yangxj.mapper2",sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourceConfig2 {
    @Bean(name = "ds2")
    DataSource dataSource2(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://119.23.57.79:3306/ds2?characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }
    @Bean(name = "sqlSessionFactory2")
    SqlSessionFactory sqlSessionFactory2() throws Exception {
        final SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource2());
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mapper2/*.xml"));
        return factory.getObject();
    }
    @Bean
    DataSourceTransactionManager transactionManager2(){
        return new DataSourceTransactionManager(dataSource2());
    }
//    @Bean(name = "sqlSessionTemplate2")
//    SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory).;
//    }
}
