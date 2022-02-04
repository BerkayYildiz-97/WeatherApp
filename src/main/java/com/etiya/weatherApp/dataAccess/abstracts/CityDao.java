package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityDao extends MongoRepository<City,String> {
    City findByCityName(String cityName);
    boolean existsByCityId(String cityId);


}
