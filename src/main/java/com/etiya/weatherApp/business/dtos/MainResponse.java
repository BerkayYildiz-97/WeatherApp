package com.etiya.weatherApp.business.dtos;

import lombok.Data;

@Data
public class MainResponse {
    private double temp;
    private double feelsLike;
    private double temp_min;
    private double temp_max;
    private long pressure;
    private long humidity;
}
