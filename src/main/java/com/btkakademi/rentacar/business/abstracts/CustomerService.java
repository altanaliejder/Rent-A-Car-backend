package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Customer;

public interface CustomerService {
    DataResult<Customer> getCustomerById(int id);
}
