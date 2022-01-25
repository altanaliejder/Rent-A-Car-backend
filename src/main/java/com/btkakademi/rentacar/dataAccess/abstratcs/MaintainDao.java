package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Maintain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintainDao extends JpaRepository<Maintain,Integer> {
    List<Maintain> getMaintainByCarId(int carId);

    @Query("FROM Maintain WHERE car.id=:carId AND returnDate IS NULL ")
    List<Maintain> getMaintanenceCar(int carId);
}
