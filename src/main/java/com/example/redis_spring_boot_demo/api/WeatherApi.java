package com.example.redis_spring_boot_demo.api;

import com.example.redis_spring_boot_demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherApi {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) throws Exception{
        return weatherService.getWeatherJson(city);
    }

    @GetMapping("/reset")
    public ResponseEntity<String> resetCounter(){
        weatherService.resetApiHitCounter();
        return ResponseEntity.ok("Reset");
    }
}