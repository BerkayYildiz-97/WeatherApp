package com.etiya.weatherApp.entities;

import com.etiya.weatherApp.business.enums.QueryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "weather_reports")
public class WeatherReport {
    @Id
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
