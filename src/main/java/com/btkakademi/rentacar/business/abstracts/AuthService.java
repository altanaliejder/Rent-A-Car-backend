package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.LoginDto;
import com.btkakademi.rentacar.business.requests.auth.LoginRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;

public interface AuthService {
    DataResult<LoginDto> login(LoginRequest loginRequest);
}
