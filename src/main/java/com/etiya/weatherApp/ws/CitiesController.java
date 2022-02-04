package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.CityService;
import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.requests.city.CreateCityRequest;
import com.etiya.weatherApp.business.requests.city.DeleteCityRequest;
import com.etiya.weatherApp.business.requests.city.UpdateCityRequest;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cities")
@CrossOrigin
public class CitiesController {
    private CityService cityService;
    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {

        return this.cityService.save(createCityRequest);
    }
    @DeleteMapping("delete/{cityId}")
    public Result delete(@PathVariable(name = "cityId") @Valid String cityId) {
        this.cityService.delete(cityId);
        return new SuccessResult();
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
        this.cityService.update(updateCityRequest);
        return new SuccessResult();
    }
    @GetMapping("all")
    public DataResult<List<CitySearchListDto>> getAll(){
        return this.cityService.getAll();
    }


}
