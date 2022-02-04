package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.WeatherApiCallService;
import com.etiya.weatherApp.business.constants.WeatherConstants;
import com.etiya.weatherApp.business.dtos.WeatherApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherApiCallManager implements WeatherApiCallService {
    private RestTemplate restTemplate;

    public WeatherApiCallManager(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    @Override
    public WeatherApiResponse getWeatherResult(String cityName) {
        String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+ "&appid="+ WeatherConstants.WEATHER_API_KEY;
        ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(weatherApiUrl, WeatherApiResponse.class);

        if(response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null){
            log.info("Weather api called succesfully . Result : {}", response.getBody());
            return  response.getBody();
        }
        return new WeatherApiResponse();
    }
}
