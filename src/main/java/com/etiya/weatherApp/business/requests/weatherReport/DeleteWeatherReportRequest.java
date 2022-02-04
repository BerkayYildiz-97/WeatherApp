package com.etiya.weatherApp.business.requests.weatherReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteWeatherReportRequest {

    private String weatherReportId;
}
