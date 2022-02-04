package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.constants.Messages;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.requests.user.CreateUserRequest;
import com.etiya.weatherApp.business.requests.user.DeleteUserRequest;
import com.etiya.weatherApp.business.requests.user.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.*;
import com.etiya.weatherApp.dataAccess.abstracts.UserDao;
import com.etiya.weatherApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder brBCryptPasswordEncoder;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService,BCryptPasswordEncoder brBCryptPasswordEncoder) {
        super();
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
        this.brBCryptPasswordEncoder=brBCryptPasswordEncoder;
    }


    @Override
    public DataResult<UserSearchListDto> save(CreateUserRequest createUserRequest) {
        User user =modelMapperService.forRequest().map(createUserRequest,User.class);
        user.setPassword(this.brBCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        User newUser = this.userDao.save(user);
        return new SuccessDataResult<>(modelMapperService.forRequest().map(newUser,UserSearchListDto.class), Messages.USERADD);
    }

    @Override
    public Result delete(String userId) {
        Result result = BusinessRules.run(existsByUserId(userId));

        if (result != null) {
            return result;
        }

        this.userDao.deleteById(userId);
        return new SuccessResult(Messages.USERDELETE);
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        Result result = BusinessRules.run(existsByUserId(updateUserRequest.getUserId()));

        if (result != null) {
            return result;
        }
        User user =modelMapperService.forRequest().map(updateUserRequest,User.class);
        user.setPassword(this.brBCryptPasswordEncoder.encode(updateUserRequest.getPassword()));
        this.userDao.save(user);
        return new SuccessResult(Messages.USERUPDATE);
    }

    @Override
    public DataResult<List<UserSearchListDto>> getAll() {
        List<User> result = this.userDao.findAll();
        List<UserSearchListDto> response = result.stream()
                .map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<UserSearchListDto>>(response,Messages.USERLIST);
    }

    @Override
    public Result existsByEmail(String email) {
        if (!this.userDao.existsByEmail(email)) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        if(this.userDao.existsByEmail(email)){
            var result = this.userDao.getByEmail(email);
            return new SuccessDataResult<>(result);
        }
        return new ErrorDataResult<User>(null);
    }

    private Result existsByUserId(String userId) {
        boolean result = this.userDao.existsByUserId(userId);
        if (result == false) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }


       /* List<User> result = this.userDao.getByEmail(email);
        List<UserSearchListDto> response = result.stream()
                .map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<UserSearchListDto>>(response);*/
}

