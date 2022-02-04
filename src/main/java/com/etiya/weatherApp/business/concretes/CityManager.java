package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.CityService;
import com.etiya.weatherApp.business.constants.Messages;
import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.requests.city.CreateCityRequest;
import com.etiya.weatherApp.business.requests.city.DeleteCityRequest;
import com.etiya.weatherApp.business.requests.city.UpdateCityRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.*;
import com.etiya.weatherApp.dataAccess.abstracts.CityDao;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result save(CreateCityRequest createCityRequest) {
        Result result = BusinessRules.run(existsByCityName(createCityRequest.getCityName()));

        if (result != null) {
            return result;
        }
        City city =modelMapperService.forRequest().map(createCityRequest,City.class);
        this.cityDao.save(city);
        return new SuccessResult(Messages.CITYADD);
    }

    @Override
    public Result delete(String cityId) {
        Result result = BusinessRules.run(existsByCityId(cityId));

        if (result != null) {
            return result;
        }

        this.cityDao.deleteById(cityId);
        return new SuccessResult(Messages.CITYDELETE);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {
        Result result = BusinessRules.run(existsByCityId(updateCityRequest.getCityId()));

        if (result != null) {
            return result;
        }
        City city =modelMapperService.forRequest().map(updateCityRequest,City.class);
        this.cityDao.save(city);
        return new SuccessResult(Messages.CITYUPDATE);
    }

    @Override
    public DataResult<List<CitySearchListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CitySearchListDto> response = result.stream()
                .map(city -> modelMapperService.forDto().map(city, CitySearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CitySearchListDto>>(response,Messages.CITYLIST);
    }
    private Result existsByCityName(String cityName) {
        City city = this.cityDao.findByCityName(cityName);
        if (city != null) {
            return new ErrorResult(Messages.EXISTBYCITYNAME);
        }
        return new SuccessResult();
    }
    private Result existsByCityId(String cityId) {
        boolean result = this.cityDao.existsByCityId(cityId);
        if (result == false) {
            return new ErrorResult(Messages.EXISTBYCITYID);
        }
        return new SuccessResult();
    }
}
