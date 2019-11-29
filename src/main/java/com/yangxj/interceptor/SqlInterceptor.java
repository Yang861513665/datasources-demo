package com.yangxj.interceptor;

import com.yangxj.util.DateUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author yangxj
 * @date 2019-11-29 10:20
 * @desc mybatis 拦截器： 输出 执行sql（带参数）， 不支持 批量操作...
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SqlInterceptor implements Interceptor {
    final static Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        BoundSql boundSql = statement.getBoundSql(args[1]);
        Object parameterObject = boundSql.getParameterObject();
        Class<?> clazz = parameterObject.getClass();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql();
        try {
            for (ParameterMapping parameterMapping : parameterMappings) {
                String property = parameterMapping.getProperty();
                sql = getFullSql(parameterObject, clazz, sql, property);
            }
        } catch (Exception e) {
           logger.info(" not support ....");
        }
        logger.info("执行 sql 操作 : {}", sql.replaceAll("\n",""));
        return invocation.proceed();
    }

    private String getFullSql(Object parameterObject, Class<?> clazz, String sql, String propertyChain) throws NoSuchFieldException, IllegalAccessException {
        // mybatis，mapper 接口 多参数 是 map类型
        Object param;
        // 包含"." 说明为 深度获取对象属性
        String property = propertyChain.contains(".") ? propertyChain.substring(0, propertyChain.indexOf(".")) : propertyChain;
        if (parameterObject instanceof Map) {
            Map map = (Map) parameterObject;
            param = map.get(property);
        } else {
            Field field = clazz.getDeclaredField(property);
            field.setAccessible(true);
            param = field.get(parameterObject);
        }
        if (param == null) {
            sql = sql.replaceFirst("\\?", null);
            // String 类型 加上 ''
        } else if (param instanceof String) {
            sql = sql.replaceFirst("\\?", "'" + param.toString() + "'");
            // 基本类型和 基本类型包装类 直接替换
        } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass())) {
            sql = sql.replaceFirst("\\?", param.toString());
            // Date 类型 处理
        } else if (param instanceof Date) {
            sql = sql.replaceFirst("\\?", "'" + DateUtils.formate((Date) param) + "'");
        } else {
            // 参数依旧 是对象 ，递归调用
            return getFullSql(param, param.getClass(), sql, propertyChain.substring(propertyChain.indexOf(".") + 1));
        }
        return sql;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
