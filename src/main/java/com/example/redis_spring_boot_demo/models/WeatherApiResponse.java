package com.example.redis_spring_boot_demo.models;

import java.time.LocalDateTime;

public class WeatherApiResponse {

    private String city;
    private double temperature;
    private double humidity;
    private String condition;
    private LocalDateTime fetchedAt;

    public WeatherApiResponse() {
    }

    public WeatherApiResponse(String city, double temperature, double humidity, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
        this.fetchedAt = LocalDateTime.now();
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }
}