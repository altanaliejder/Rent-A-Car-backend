package com.btkakademi.rentacar.business.requests.rentalAdditionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalAdditionalServiceRequest {
    private int rentalId;
    private int additionalId;
}
