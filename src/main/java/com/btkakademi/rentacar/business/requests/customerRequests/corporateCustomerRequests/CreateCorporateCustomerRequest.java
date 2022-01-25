package com.btkakademi.rentacar.business.requests.customerRequests.corporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
    private String email;
    private String password;
    private String companyName;
    private String taxNumber;
}
