package com.etiya.weatherApp.business.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherReportsFilterDto {
    private String cityName;
    private String userId;
    private Date startDate;
    private Date endDate;
}
