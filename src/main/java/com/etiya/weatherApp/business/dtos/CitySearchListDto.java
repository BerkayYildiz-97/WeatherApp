package com.etiya.weatherApp.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitySearchListDto {
    private String cityId;

    private String cityName;
}
