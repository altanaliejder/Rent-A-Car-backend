package com.btkakademi.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintainListDto {
    private LocalDate maintainDate;
    private LocalDate returnDate;
    private int carId;
}
