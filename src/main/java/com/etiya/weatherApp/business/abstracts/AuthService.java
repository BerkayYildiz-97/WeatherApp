package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.requests.authenticaiton.UserLoginRequest;
import com.etiya.weatherApp.business.requests.authenticaiton.UserRegisterRequest;
import com.etiya.weatherApp.core.utilities.results.Result;

public interface AuthService {


    Result userLogin(UserLoginRequest userLoginRequest);

}
