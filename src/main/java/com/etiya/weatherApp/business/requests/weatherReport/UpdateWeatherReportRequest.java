package com.etiya.weatherApp.business.requests.weatherReport;

import com.etiya.weatherApp.business.enums.QueryStatus;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWeatherReportRequest {

    private String weatherReportId;

    private String weatherCondition;

    private String temperature;

    private Timestamp queryTime;

    private Timestamp queryDate;

    private String ipAddress;

    private Enum<QueryStatus> queryStatusEnum;

    private String userId;

    private String cityId;
}
