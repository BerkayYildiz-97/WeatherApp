package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.WeatherApiCallService;
import com.etiya.weatherApp.business.abstracts.WeatherReportService;
import com.etiya.weatherApp.business.dtos.WeatherApiResponse;
import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.dtos.WeatherReportsFilterDto;
import com.etiya.weatherApp.business.requests.weatherReport.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.requests.weatherReport.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.ExecutionTime;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessDataResult;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import com.etiya.weatherApp.dataAccess.abstracts.CityDao;
import com.etiya.weatherApp.dataAccess.abstracts.QueryTimeDao;
import com.etiya.weatherApp.dataAccess.abstracts.UserDao;
import com.etiya.weatherApp.dataAccess.abstracts.WeatherReportDao;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import com.etiya.weatherApp.entities.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherReportManager implements WeatherReportService {
    private WeatherReportDao weatherReportDao;
    private ModelMapperService modelMapperService;
    private QueryTimeDao queryTimeDao;
    private CityDao cityDao;
    private WeatherApiCallService weatherApiCallService;
    private UserDao userDao;
    private MongoTemplate mongoTemplate;


    @Autowired
    public WeatherReportManager(WeatherReportDao weatherReportDao, ModelMapperService modelMapperService, QueryTimeDao queryTimeDao,
                                CityDao cityDao, WeatherApiCallService weatherApiCallService, UserDao userDao,MongoTemplate mongoTemplate) {
        this.weatherReportDao = weatherReportDao;
        this.modelMapperService = modelMapperService;
        this.queryTimeDao=queryTimeDao;
        this.cityDao=cityDao;
        this.weatherApiCallService=weatherApiCallService;
        this.userDao=userDao;
        this.mongoTemplate=mongoTemplate;
    }

    @Override
    public Result save(CreateWeatherReportRequest createWeatherReportRequest) {
        ExecutionTime executionTime = new ExecutionTime(true);
        WeatherReport weatherReport =modelMapperService.forRequest().map(createWeatherReportRequest,WeatherReport.class);

        City city = cityDao.findByCityName(createWeatherReportRequest.getCityName());
        WeatherApiResponse apiResponse = weatherApiCallService.getWeatherResult(createWeatherReportRequest.getCityName());
        if(apiResponse.getWeather().length != 0 && apiResponse.getMain() != null){
            weatherReport.setTemperature(apiResponse.getMain().getTemp()-273.15);
            weatherReport.setWeatherCondition(apiResponse.getWeather()[0].getDescription());
        }

        weatherReport.setCity(city);

        User user  = userDao.findByEmail(createWeatherReportRequest.getEmail());
        weatherReport.setUser(user);
        weatherReport.setQueryDate(new java.util.Date());
        executionTime.endTask();
        weatherReport.setQueryTime(executionTime.duration());

        this.weatherReportDao.save(weatherReport);
        return new SuccessDataResult<>(modelMapperService.forRequest().map(weatherReport, WeatherReportSearchListDto.class), "success");

        //Date queryDate = new java.sql.Date(new java.util.Date().getTime());
        //weatherReport.setQueryDate((java.sql.Date) queryDate);


        //Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        //weatherReport.setQueryTime(timestamp1.getTime());
        //this.weatherReportDao.save(weatherReport);
        //Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        //long timestampbetween = timestamp2.getTime()-timestamp1.getTime();
        //User user = new User();
        //user.setUserId(createWeatherReportRequest.getUserId());
        //QueryTime queryTime = new QueryTime("1",timestampbetween,2,user);
        //this.queryTimeDao.save(queryTime);
        //System.out.println(timestampbetween);
        //return new SuccessResult();
    }

    @Override
    public Result delete(DeleteWeatherReportRequest deleteWeatherReportRequest) {
        WeatherReport weatherReport =modelMapperService.forRequest().map(deleteWeatherReportRequest,WeatherReport.class);
        this.weatherReportDao.delete(weatherReport);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateWeatherReportRequest updateWeatherReportRequest) {
        WeatherReport weatherReport =modelMapperService.forRequest().map(updateWeatherReportRequest,WeatherReport.class);
        this.weatherReportDao.save(weatherReport);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<WeatherReportSearchListDto>> getAll() {
        List<WeatherReport> result = this.weatherReportDao.findAll();
        List<WeatherReportSearchListDto> response = result.stream()
                .map(weatherReport -> modelMapperService.forDto().map(weatherReport, WeatherReportSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<WeatherReportSearchListDto>>(response);
    }

    @Override
    public DataResult<List<WeatherReportSearchListDto>> getWeatherReportsWithFilter(WeatherReportsFilterDto request) {
        Criteria criteria = new Criteria();
        if (request.getCityName() != null) {
            criteria = criteria.and("city.cityName").is(request.getCityName());
        }
        if (request.getUserId() != null) {
            criteria = criteria.and("user.userId").is(request.getUserId());
        }
        if (request.getStartDate() != null && request.getEndDate() == null) {
            criteria = criteria.and("queryDate").gte(request.getStartDate());
        }

        if (request.getEndDate() != null && request.getStartDate() == null) {
            criteria = criteria.and("queryDate").lte(request.getEndDate());
        }

        if (request.getStartDate() != null && request.getEndDate() != null) {
            criteria = criteria.and("queryDate").gte(request.getStartDate()).lte(request.getEndDate());
        }

        Query query = new Query(criteria);
        List<WeatherReport> result = this.mongoTemplate.find(query, WeatherReport.class);
        List<WeatherReportSearchListDto> response = result.stream().map(weatherReport -> modelMapperService.forDto()
                .map(weatherReport, WeatherReportSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }


}
