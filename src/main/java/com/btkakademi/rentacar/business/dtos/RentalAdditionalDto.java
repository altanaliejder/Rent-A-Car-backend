package com.btkakademi.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAdditionalDto {
    private int id;
    private int rentalId;
    private int additionalId;
}
