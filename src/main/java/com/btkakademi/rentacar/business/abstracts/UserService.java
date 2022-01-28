package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.entities.concretes.User;

public interface UserService {
    DataResult<User> getById(int id);
    DataResult<User> getByEmail(String email);
}
