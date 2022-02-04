package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.dtos.WeatherReportsFilterDto;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.business.requests.weatherReport.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;

import java.util.List;

public interface WeatherReportService {
    Result save(CreateWeatherReportRequest createWeatherReportRequest);
    Result delete(DeleteWeatherReportRequest deleteWeatherReportRequest);
    Result update(UpdateWeatherReportRequest updateWeatherReportRequest);

    DataResult<List<WeatherReportSearchListDto>> getAll();
    DataResult<List<WeatherReportSearchListDto>> getWeatherReportsWithFilter(WeatherReportsFilterDto request);
}
