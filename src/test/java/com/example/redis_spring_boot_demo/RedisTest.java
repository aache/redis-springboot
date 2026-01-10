package com.example.redis_spring_boot_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate<String, String> template;

    @Test
    public void test(){
        template.opsForValue().set("email", "test@redisemail.com");
        System.out.println(template.opsForValue().get("email"));
        System.out.println(System.getProperty("java.version"));
    }

}
