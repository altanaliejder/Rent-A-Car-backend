package com.btkakademi.rentacar.business.requests.additionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {
    private String name;
    private int rentalId;
}
