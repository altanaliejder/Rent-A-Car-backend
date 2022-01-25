package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;

public interface CreditCardService {
    Result add(CreateCreditCardRequest createCreditCardRequest);
}
