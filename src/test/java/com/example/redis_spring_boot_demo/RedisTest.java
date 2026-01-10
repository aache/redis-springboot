package com.example.redis_spring_boot_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Test
    void testStringWithTTL() throws InterruptedException{
        //Setting Otp string with a timeout of 3 Seconds
        template.opsForValue().set("otp", "123456", 3, TimeUnit.SECONDS);
        String otp = template.opsForValue().get("otp");
        System.out.println("OTP = " + otp);
        Thread.sleep(3000);
        String otpAfter3Seconds = template.opsForValue().get("otp");
        System.out.println(otpAfter3Seconds);
        Assertions.assertNull(otpAfter3Seconds);
    }

    @Test
    void testAtomicCounter() {
        String key = "login:attempts";
        template.opsForValue().set(key, "0");
        Long count1 = template.opsForValue().increment(key);
        Long count2 = template.opsForValue().increment(key);

        Assertions.assertEquals(1, count1);
        Assertions.assertEquals(2, count2);

        Long count3 = template.opsForValue().increment(key);
        Long count4 = template.opsForValue().increment(key);
        Assertions.assertEquals(3, count3);
        if (count4 != null && count4 > 3) {
            template.opsForValue().set(key, "0");
        }
        String finalValue = template.opsForValue().get(key);

        Assertions.assertEquals("0", finalValue);
    }

    @Test
    void getAllKeysUnsafeButSimple() {
        Set<String> keys = template.keys("*");
        System.out.println(keys);
    }
}
