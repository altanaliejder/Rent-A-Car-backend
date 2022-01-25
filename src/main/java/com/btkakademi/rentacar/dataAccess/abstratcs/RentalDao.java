package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalDao extends JpaRepository<Rental,Integer> {

    @Query("FROM Rental WHERE car.id=:carId and returnDate IS NULL ")
    List<Rental> getRentalCar(int carId);

    Rental getRentalById(int rentalId);
    Rental getRentalsByCustomerId(int customerId);

}
