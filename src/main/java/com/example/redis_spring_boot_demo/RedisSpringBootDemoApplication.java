package com.example.redis_spring_boot_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootDemoApplication.class, args);
	}

}
