package com.gaoap.learning.demostarter.config;

import com.gaoap.learning.demostarter.properties.HelloWorldProperties;
import com.gaoap.learning.demostarter.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot自动注入的奥秘就来源于 Spring Boot应用在启动过程中会通过 SpringFactoriesLoader
 * 加载所有依赖的 META-INF/spring.factories 文件，通过一系列的处理流程最终将 spring.factories
 * 文件中的定义的各种 beans 装载入 ApplicationContext容器。总之要配置文件：src/main/resources/META-INF/spring.factories
 *  src/main/resources/META-INF/spring-configuration-metadata.json
 * 元数据文件，提供所有支持的配置属性的详细信息。这些文件旨在允许IDE开发人员在用户使用application.properties
 * 或application.yml文件时提供上下文帮助和自动补全
 *
 * 条件注解说明：条件注解 ConditionalOnBean、ConditionalOnMissingBean、ConditionalOnClass、ConditionalOnMissingClass
 * @ConditionalOnBean         	当给定的在bean存在时,则实例化当前Bean
 * @ConditionalOnMissingBean  	当给定的在bean不存在时,则实例化当前Bean
 * @ConditionalOnClass        	当给定的类名在类路径上存在，则实例化当前Bean
 * @ConditionalOnMissingClass 	当给定的类名在类路径上不存在，则实例化当前Bean
 *
 *
 * @author gaoyd
 * @version 1.0.0
 * @ClassName HelloWorldAutoConfiguration.java
 * @Description TODO
 * @createTime 2021年10月10日 19:06:00
 */
@ConditionalOnClass(HelloWorldProperties.class)
@Configuration
@EnableConfigurationProperties(HelloWorldProperties.class)
@ConditionalOnProperty(
        prefix = "helloworld",
        name = "isopen",
        havingValue = "true"
)
public class HelloWorldAutoConfiguration {
    @Autowired
    private HelloWorldProperties helloWorldProperties;

    @Bean(name = "helloWorldService")
    @ConditionalOnMissingBean(name="helloWorldService")
    public HelloWorldService helloWorldService() {
        return new HelloWorldService(helloWorldProperties);
    }
}
