package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.customerRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.CorporateCustomer;
import com.btkakademi.rentacar.entities.concretes.IndividualCustomer;

public interface CorporateCustomerService {
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    DataResult<CorporateCustomer> getCorporateByTaxNumber(String identityNumber);
    DataResult<CorporateCustomer> getByCorporateId(int id);
}
