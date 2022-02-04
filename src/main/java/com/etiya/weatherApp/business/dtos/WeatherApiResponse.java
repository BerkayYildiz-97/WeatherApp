package com.etiya.weatherApp.business.dtos;

import lombok.Data;

@Data
public class WeatherApiResponse {
    private Weather[] weather;
    private MainResponse main;
}
