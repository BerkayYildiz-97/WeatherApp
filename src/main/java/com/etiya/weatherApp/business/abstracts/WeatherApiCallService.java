package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.dtos.WeatherApiResponse;

public interface WeatherApiCallService {
    WeatherApiResponse getWeatherResult(String cityName);
}
