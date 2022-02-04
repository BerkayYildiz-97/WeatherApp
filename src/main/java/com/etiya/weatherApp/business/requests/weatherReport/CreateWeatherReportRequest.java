package com.etiya.weatherApp.business.requests.weatherReport;

import com.etiya.weatherApp.business.enums.QueryStatus;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWeatherReportRequest {

    private String email;

    private String cityName;

    @JsonIgnore
    private String ipAddress;

    @JsonIgnore
    private Date queryDate;



    //private String weatherCondition;

   // private String temperature;
    //@JsonIgnore
   // private Timestamp queryTime;
   // @JsonIgnore
   // private Timestamp queryDate;

    //private String ipAddress;

    //private Enum<QueryStatus> queryStatusEnum;

    //private User user;

    //private City city;
}
