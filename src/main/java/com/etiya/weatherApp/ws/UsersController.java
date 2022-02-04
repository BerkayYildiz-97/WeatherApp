package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
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
@RequestMapping("api/users")
@CrossOrigin
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }
    @PostMapping("add")
	public DataResult<UserSearchListDto> add(@RequestBody @Valid CreateUserRequest createUserRequest) {
		return this.userService.save(createUserRequest);
	}
    @CrossOrigin
    @DeleteMapping("delete/{userId}")
    public Result delete(@PathVariable(name = "userId") @Valid String userId) {
        this.userService.delete(userId);
        return new SuccessResult();
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        this.userService.update(updateUserRequest);
        return new SuccessResult();
    }
    @GetMapping("all")
    public DataResult<List<UserSearchListDto>> getAll(){
        return this.userService.getAll();
    }



}
