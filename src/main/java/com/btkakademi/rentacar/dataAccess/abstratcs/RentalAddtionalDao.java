package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.RentalAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalAddtionalDao extends JpaRepository<RentalAdditionalService,Integer> {

    List<RentalAdditionalService> getRentalAdditionalServicesByRentalId(int rentalId);
}
