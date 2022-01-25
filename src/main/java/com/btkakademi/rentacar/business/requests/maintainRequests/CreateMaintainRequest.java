package com.btkakademi.rentacar.business.requests.maintainRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintainRequest {
    private LocalDate maintainDate;
    private int carId;
}
