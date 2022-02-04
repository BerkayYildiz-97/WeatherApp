package com.etiya.weatherApp.business.requests.authenticaiton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRequest {
    private String email;

    private String password;
}
