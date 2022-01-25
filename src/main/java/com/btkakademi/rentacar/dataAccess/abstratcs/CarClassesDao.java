package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.CarClasses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassesDao extends JpaRepository<CarClasses, Integer> {
}
