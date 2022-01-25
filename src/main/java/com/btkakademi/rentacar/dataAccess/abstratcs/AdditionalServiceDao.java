package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService,Integer> {
    AdditionalService getAdditionalServiceByName(String additionalServiceName);
}
