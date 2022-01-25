package com.btkakademi.rentacar.business.requests.paymentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    private int rentalId;
    private String code;
    private String holder;
    private String cardNumber;
    private String cvv;
    private String expirationDate;

}