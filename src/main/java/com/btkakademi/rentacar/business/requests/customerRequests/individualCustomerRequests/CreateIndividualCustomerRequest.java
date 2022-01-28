package com.btkakademi.rentacar.business.requests.customerRequests.individualCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String identityNumber;
}
