package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.WeatherReportService;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.dtos.WeatherReportsFilterDto;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.business.requests.weatherReport.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.HttpUtils;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/weatherReports")
@CrossOrigin
public class WeatherReportsController {

    private WeatherReportService weatherReportService;
    private HttpServletRequest httpRequest;

    @Autowired
    public WeatherReportsController(WeatherReportService weatherReportService,HttpServletRequest httpRequest) {
        this.weatherReportService = weatherReportService;
        this.httpRequest=httpRequest;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateWeatherReportRequest createWeatherReportRequest) {
        String ip = HttpUtils.getRequestIP(httpRequest);
        createWeatherReportRequest.setIpAddress(ip);
        return this.weatherReportService.save(createWeatherReportRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteWeatherReportRequest deleteWeatherReportRequest) {
        this.weatherReportService.delete(deleteWeatherReportRequest);
        return new SuccessResult();
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateWeatherReportRequest updateWeatherReportRequest) {
        this.weatherReportService.update(updateWeatherReportRequest);
        return new SuccessResult();
    }
    @GetMapping("all")
    public DataResult<List<WeatherReportSearchListDto>> getAll(){

        return this.weatherReportService.getAll();
    }
    @PostMapping("getWeatherReportsWithFilter")
    public DataResult<List<WeatherReportSearchListDto>> getWeatherReportsWithFilter(@RequestBody WeatherReportsFilterDto request){
        return   this.weatherReportService.getWeatherReportsWithFilter(request);
    }

}
