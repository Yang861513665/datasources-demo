package com.yangxj.config;

import com.yangxj.interceptor.SqlInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxj
 * @date 2019-11-28 20:02
 */
@Configuration
@MapperScan(basePackages = {"com.yangxj.mapper1","com.yangxj.mapper2"})
public class DynamicDataSourceConfig {

    @Bean(name = "ds1")
    DataSource dataSource1(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ds1?characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }
    @Bean(name = "ds2")
    DataSource dataSource2(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ds2?characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }

    @Bean(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dataSource, Interceptor sqlInterceptor) throws Exception {
        final SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
        factory.setPlugins(new Interceptor[]{sqlInterceptor});
        return factory.getObject();
    }
    @Bean
    Interceptor sqlInterceptor(){
        return new SqlInterceptor();
    }
    @Bean("dynamicDataSource")
    DynamicDataSource dynamicDataSource(DataSource ds1, DataSource ds2){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>(2);
        map.put("ds1",ds1);
        map.put("ds2",ds2);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }
}
