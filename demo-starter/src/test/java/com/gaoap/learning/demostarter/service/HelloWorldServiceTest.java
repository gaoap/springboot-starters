package com.gaoap.learning.demostarter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 注解-意义
 *
 * @author gaoyd
 * @version 1.0.0
 * @RunWith(SpringRunner.class): 标识为Spring提供的JUnit运行环境；
 * @SpringBootTest(classes = {vip.hoody.config.MyConfiguration.class})
 * 不同于完整的Springboot项目,单独的starter没有Application.class所以需要指定环境需要加载的Configuration文件,
 * 此处的classes的值是数组,根据测试的覆盖范围需要把涉及到的Configuration文件写入
 * @Test: Junit的测试方法, 需要待测的方法必须加入此注解,
 * 否则或报错java.lang.Exception: No tests found matching Method
 * @TestPropertySource("classpath:test.properties") 指示测试时读取resource/test.properties作为配置文件,因为作为一个Starter,
 * 运行时读取依赖它的应用的配置文件,所以测试中需要指定一个配置文件作为数据来源
 * @ClassName HelloWorldServiceTest.java
 * @Description TODO
 * @createTime 2021年10月10日 20:27:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.gaoap.learning.demostarter.config.HelloWorldAutoConfiguration.class})
@TestPropertySource("classpath:test.properties")
public class HelloWorldServiceTest {
    @Autowired
    HelloWorldService helloWorldService;


    @Test
    public void getInfo() {
        System.out.println("中文测试");
        System.out.println(helloWorldService.sayHelloWorld());
        assertEquals("世界真大!  你好你好 for 8888888", helloWorldService.sayHelloWorld());
    }


}
