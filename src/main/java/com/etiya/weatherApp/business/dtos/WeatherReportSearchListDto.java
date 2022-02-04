package com.etiya.weatherApp.business.dtos;

import com.etiya.weatherApp.business.enums.QueryStatus;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReportSearchListDto {

    private String weatherReportId;

    private String weatherCondition;

    private double temperature;

    private long queryTime;

    private Date queryDate;

    private String ipAddress;

    private Enum<QueryStatus> queryStatusEnum;

    private User user;

    private City city;
}
