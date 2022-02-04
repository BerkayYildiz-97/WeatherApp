package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.requests.city.CreateCityRequest;
import com.etiya.weatherApp.business.requests.city.DeleteCityRequest;
import com.etiya.weatherApp.business.requests.city.UpdateCityRequest;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    Result save(CreateCityRequest createCityRequest);
    Result delete(String cityId);
    Result update(UpdateCityRequest updateCityRequest);

    DataResult<List<CitySearchListDto>> getAll();
}
