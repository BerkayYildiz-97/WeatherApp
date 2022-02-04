package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.AuthService;
import com.etiya.weatherApp.business.requests.authenticaiton.UserLoginRequest;
import com.etiya.weatherApp.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auths")
@CrossOrigin
public class AuthsController {
    private AuthService authService;

    @Autowired
    public AuthsController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    Result login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return this.authService.userLogin(userLoginRequest);
    }


}
