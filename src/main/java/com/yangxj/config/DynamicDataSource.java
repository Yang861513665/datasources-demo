package com.yangxj.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yangxj
 * @date 2019-11-28 19:29
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get() == null ? DataSourceContextHolder.DEFAULT_DS : DataSourceContextHolder.get();
    }
}
