package com.btkakademi.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualInvoiceListDto {
    private String carDesciption;
    private String brand;
    private String color;
    private double dailyPrice;
    private double totalPrice;
    private List<String> additionalService;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private String customerName;
    private String customerSurname;
    private String identityNumber;
}
//bir kere oluştuysa bir daha oluşturulamaz