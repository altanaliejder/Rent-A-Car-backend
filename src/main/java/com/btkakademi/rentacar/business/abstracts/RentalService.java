package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Rental;

public interface RentalService {
    DataResult<Rental> individualAdd(CreateRentalRequest createRentalRequest);
    Result checkRentalCar(int carId);
    DataResult<Rental> getRentalById(int rentalId);
    Result checkIfInRental(int rentalId);
    Result corporateAdd(CreateRentalRequest createRentalRequest);
    DataResult<Rental>  getRentalsByCustomerId(int customerId);

}
