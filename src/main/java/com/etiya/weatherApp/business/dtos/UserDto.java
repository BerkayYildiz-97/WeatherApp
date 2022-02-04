package com.etiya.weatherApp.business.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;
}
