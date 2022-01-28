package com.btkakademi.rentacar.business.requests.rentalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    private LocalDate returnDate;
    private int rentKilometer;
    private int returnedKilometer;
    private int pickUpCityId;
    private int returnCityId;
    private int customerId;
    private int carId;
}

//return date rent_dateden önce olamaz
//return km rent kmden düşük olmaz
//müşteri id