package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.QueryTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QueryTimeDao extends MongoRepository<QueryTime,String> {
}
