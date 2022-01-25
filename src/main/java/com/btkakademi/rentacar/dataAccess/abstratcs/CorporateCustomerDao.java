package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer,Integer> {
    CorporateCustomer getCorporateCustomerByEmail(String email);
    CorporateCustomer getCorporateCustomerByCompanyName(String companyName);
    CorporateCustomer getCorporateCustomerByTaxNumber(String taxNumber);
}
