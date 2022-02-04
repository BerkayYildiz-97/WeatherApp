package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.entities.User;

import java.util.List;

public interface UserService {
    DataResult<UserSearchListDto> save(CreateUserRequest createUserRequest);
    Result delete(String userId);
    Result update(UpdateUserRequest updateUserRequest);

    DataResult<List<UserSearchListDto>> getAll();

    Result existsByEmail(String email);
    DataResult<User> getByEmail(String email);
}
