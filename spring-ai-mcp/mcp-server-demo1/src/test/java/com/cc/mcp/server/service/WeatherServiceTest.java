package com.cc.mcp.server.service;

import org.junit.jupiter.api.Test;

class WeatherServiceTest {
    WeatherService weatherService = new WeatherService();

    @Test
    void getWeatherForecastByLocation() {
        System.out.println(weatherService.getWeatherForecastByLocation(47.6062, -122.3321));
    }

    @Test
    void getAlerts() {
        System.out.println(weatherService.getAlerts("NY"));
    }

}
