package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.customerRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerService {
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    DataResult<IndividualCustomer> getIndividualById(int id);
    DataResult<IndividualCustomer> getIndividualByIdentity(String identityNumber);
}
