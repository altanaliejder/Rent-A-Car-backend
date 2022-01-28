package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.AuthService;
import com.btkakademi.rentacar.business.abstracts.UserService;
import com.btkakademi.rentacar.business.dtos.LoginDto;
import com.btkakademi.rentacar.business.requests.auth.LoginRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private ModelMapperService modelMapperService;
    private UserService userService;

    public AuthManager(ModelMapperService modelMapperService, UserService userService) {
        this.modelMapperService = modelMapperService;
        this.userService = userService;
    }

    @Override
    public DataResult<LoginDto> login(LoginRequest loginRequest) {
        var result=BusinessRules.run(checkIfEmailExist(loginRequest.getEmail()),checkIfPasswordCorrect(loginRequest.getEmail(),loginRequest.getPassword()));
        if (result==null){
            var user=this.userService.getByEmail(loginRequest.getEmail()).getData();
            var dto=this.modelMapperService.forRequest().map(user,LoginDto.class);
            return new SuccessDataResult<LoginDto>(dto);
        }return new ErrorDataResult<>(result.getMessage());
    }



    private Result checkIfEmailExist(String email){
        if(!userService.getByEmail(email).isSuccess()){
            return new ErrorResult("Böyle bir kullanıcı yok");
        }return new SuccessResult();
    }

    private Result checkIfPasswordCorrect(String email,String password){
        var user=this.userService.getByEmail(email).getData();
        if (!user.getPassword().equals(password)){
            return new ErrorResult("Şifre Yanlış");
        }return new SuccessResult();
    }
}
