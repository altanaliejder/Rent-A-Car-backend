package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {
}
