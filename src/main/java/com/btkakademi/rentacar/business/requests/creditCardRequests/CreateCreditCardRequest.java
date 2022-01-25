package com.btkakademi.rentacar.business.requests.creditCardRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest  {
    private String holder;
    private String cvv;
    private String cardNumber;
    private String expirationDate;
    private int customerId;

}
