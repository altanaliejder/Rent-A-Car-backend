package com.btkakademi.rentacar.adapters.pos;

import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.CreditCard;

public interface PosService {
    Result checkIfLimit(String holder,String creditCardNumber,String cvv,String validateDate);
}
