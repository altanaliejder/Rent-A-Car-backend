package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardDao extends JpaRepository<CreditCard,Integer> {
}
