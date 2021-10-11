package com.gaoap.learning.multipleds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@ConditionalOnProperty(name = "spring.datasource.primary")
public class DataSourceAutoConfig {
    @Bean(name = "firstDataSource")
    @ConditionalOnMissingBean(name = "firstDataSource")
    @ConditionalOnProperty(name = "spring.datasource.primary")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource firstDataSource() {
        log.info("first db built");
        System.out.println("first db built");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    @ConditionalOnMissingBean(name = "secondDataSource")
    @ConditionalOnProperty(name = "spring.datasource.secondary")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondDataSource() {
        log.info("second db built");
        System.out.println("second db built");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND, secondDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(firstDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }
}
