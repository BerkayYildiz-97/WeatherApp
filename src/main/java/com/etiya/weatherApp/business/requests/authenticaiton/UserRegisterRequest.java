package com.etiya.weatherApp.business.requests.authenticaiton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterRequest {
    private String email;

    private String firstName;

    private String lastName;

    private String password;
}
