package com.btkakademi.rentacar.business.requests.carRequests;

import com.btkakademi.rentacar.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @NotNull
    private double dailyPrice;
    private int modelYear;
    private String description;
    private int findexScore;
    private int kilometer;
    private int colorId;
    private int brandId;

}
