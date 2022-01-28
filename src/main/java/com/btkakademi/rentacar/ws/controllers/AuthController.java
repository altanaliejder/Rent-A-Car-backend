package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.AuthService;
import com.btkakademi.rentacar.business.dtos.LoginDto;
import com.btkakademi.rentacar.business.requests.auth.LoginRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

     @PostMapping("login")
    public DataResult<LoginDto> login(@RequestBody LoginRequest loginRequest){
        return this.authService.login(loginRequest);
     }
}
