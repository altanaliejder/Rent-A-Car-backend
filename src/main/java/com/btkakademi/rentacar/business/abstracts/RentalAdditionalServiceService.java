package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.rentalAdditionalRequests.CreateRentalAdditionalServiceRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.RentalAdditionalService;

import java.util.List;

public interface RentalAdditionalServiceService {
    DataResult<List<RentalAdditionalService>> getAdditionalByRentalId(int rentalId);
    Result add(CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest);
}
