package com.example.redis_spring_boot_demo.service;

import com.example.redis_spring_boot_demo.models.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

@Service
public class WeatherService {

    private static final Duration CACHE_TTL = Duration.ofMinutes(1);

    private final String API_HIT_COUNTER = "api:getWeather:count";

    @Autowired
    RedisTemplate<String, String> template;

    ObjectMapper objectMapper = new ObjectMapper();

    @Cacheable(value = "weather", key = "#city.toLowerCase()")
    public String getWeatherJson(String city) throws Exception {
        WeatherApiResponse response = fetchFromExternalApi(city);
        template.opsForValue().increment(API_HIT_COUNTER);
        System.out.println("Calling actual api");
        return objectMapper.writeValueAsString(response);
    }
    private WeatherApiResponse fetchFromExternalApi(String city) {

        // In real life:
        // String url = "https://api.weatherapi.com/v1/current.json?key=API_KEY&q=" + city;
        // return restTemplate.getForObject(url, WeatherApiResponse.class);

        // For demo: simulate weather that changes every call
        double temp = 20 + Math.random() * 15;
        double humidity = 50 + Math.random() * 40;

        return new WeatherApiResponse(
                city,
                Math.round(temp * 10.0) / 10.0,
                Math.round(humidity * 10.0) / 10.0,
                "Sunny"
        );
    }


    public void resetApiHitCounter(){
        System.out.println("Current API Hit Count : " + template.opsForValue().get(API_HIT_COUNTER));
        template.opsForValue().set(API_HIT_COUNTER, "0");
    }
}