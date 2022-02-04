package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.AuthService;
import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.constants.Messages;
import com.etiya.weatherApp.business.dtos.UserDto;
import com.etiya.weatherApp.business.requests.authenticaiton.UserLoginRequest;
import com.etiya.weatherApp.business.requests.authenticaiton.UserRegisterRequest;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.ErrorResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessDataResult;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private UserService userService;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthManager(UserService userService,ModelMapperService modelMapperService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.modelMapperService = modelMapperService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public Result userLogin(UserLoginRequest userLoginRequest) {
        Result result = BusinessRules.run(checkUserEmail(userLoginRequest), checkUserPassword(userLoginRequest));
        if (result != null) {
            return result;
        }
        return new SuccessDataResult<>(modelMapperService.forDto().map(userService.getByEmail(userLoginRequest.getEmail()).getData(), UserDto.class));
    }

    private Result checkUserEmail(UserLoginRequest userLoginRequest) {
        if (!this.userService.existsByEmail(userLoginRequest.getEmail()).isSuccess()) {
            return new ErrorResult(Messages.LOGIN);

        }
        return new SuccessResult();
    }

    private Result checkUserPassword(UserLoginRequest userLoginRequest) {
        if (checkUserEmail(userLoginRequest).isSuccess()) {
            if (!bCryptPasswordEncoder.matches(userLoginRequest.getPassword(),this.userService.getByEmail(userLoginRequest.getEmail()).getData().getPassword())) {
                return new ErrorResult(Messages.LOGIN);
            }
        }
        return new SuccessResult();
    }
}
