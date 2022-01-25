package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer,Integer> {
    IndividualCustomer getIndividualCustomerByEmail(String email);
    IndividualCustomer getIndividualCustomerByIdentityNumber(String identityNum);
    IndividualCustomer getIndividualCustomerById(int id);
}
