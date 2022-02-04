package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDao extends MongoRepository<User,String> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User getByEmail(String email);
    Boolean existsByUserId(String userId);
}
