package com.gaoap.learning.demostarter.service;

import com.gaoap.learning.demostarter.properties.HelloWorldProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gaoyd
 * @version 1.0.0
 * @ClassName HelloWorldService.java
 * @Description TODO
 * @createTime 2021年10月10日 09:22:00
 */
@AllArgsConstructor //Lombok注解生成全参构造器
public class HelloWorldService {
    @Getter
    @Setter
    private HelloWorldProperties helloWorldProperties;

    public String sayHelloWorld() {
        return this.helloWorldProperties.getWho() + "!  " + this.helloWorldProperties.getWhat() + " for " + this.helloWorldProperties.getAge();
    }
}
