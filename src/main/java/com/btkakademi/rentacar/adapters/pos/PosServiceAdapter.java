package com.btkakademi.rentacar.adapters.pos;

import com.btkakademi.rentacar.core.utilities.fakeServices.FakePosService;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import com.btkakademi.rentacar.entities.concretes.CreditCard;

public class PosServiceAdapter implements PosService {


    @Override
    public Result checkIfLimit(String holder,String creditCardNumber,String cvv,String validateDate) {
        FakePosService posService= new FakePosService();
        posService.makePayment(holder,creditCardNumber,cvv,validateDate);
        return new SuccessResult();
    }
}
