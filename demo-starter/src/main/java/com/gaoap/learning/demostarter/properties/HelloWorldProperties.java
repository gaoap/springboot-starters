package com.gaoap.learning.demostarter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gaoyd
 * @version 1.0.0
 * @ClassName HelloWorldProperties.java
 * @Description TODO
 * @createTime 2021年10月10日 18:49:00
 */
@Getter //@Getter/@Setter: 作用类上，生成所有成员变量的getter/setter方法；作用于成员变量上，
// 生成该成员变量的getter/setter方法。可以设定访问权限及是否懒加载等。
@Setter
@ConfigurationProperties(prefix = "helloworld")
public class HelloWorldProperties {
    private String who = "world";
    private String what = "hello";
    private Long age = -1l;
    private Boolean isopen=false;
}
