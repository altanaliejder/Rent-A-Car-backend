package com.btkakademi.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class LoginDto {
    private int id;
    private String email;
}
