package com.gaoap.learning.localtest;

import com.gaoap.learning.demostarter.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocaltestApplicationTests {
    @Autowired
    public HelloWorldService helloWorldService;

    @Test
    void contextLoads() {
       System.out.println( helloWorldService.sayHelloWorld());
        assertEquals("世界真奇妙!  宇宙浩瀚 for 99999", helloWorldService.sayHelloWorld());
    }

}
