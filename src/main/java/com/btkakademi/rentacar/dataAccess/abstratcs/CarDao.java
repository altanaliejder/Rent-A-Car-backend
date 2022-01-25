package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarDao extends JpaRepository<Car,Integer> {
    Car getCarById(int id);
    List<Car> getCarsByCarClassId(int carClassId);
//select * from cars
// left join maintains on cars.id=maintains.car_id and maintains.return_date is null
// left join rentals on cars.id=rentals.car_id and (rentals.return_date is null or rentals.return_date>NOW())
// where maintains.id is null and rentals.id is null and cars.car_class_id=3
    @Query(value = "select cars.id,cars.daily_price,cars.description,cars.findex_score,cars.kilometer,cars.model_year,cars.brand_id,cars.color_id,cars.car_class_id from cars " +
            "left join maintains on cars.id=maintains.car_id and maintains.return_date is null " +
            " left join rentals on cars.id=rentals.car_id and (rentals.return_date is null or rentals.return_date>NOW()) " +
            "where maintains.id is null and rentals.id is null and cars.car_class_id=:segmentId",nativeQuery = true)
    Car getAvailableCarsBySegment(int segmentId);

    @Query(value = "select cars.id,cars.daily_price,cars.description,cars.findex_score,cars.kilometer,cars.model_year,cars.brand_id,cars.color_id,cars.car_class_id from cars " +
            "left join maintains on cars.id=maintains.car_id and maintains.return_date is null " +
            " left join rentals on cars.id=rentals.car_id and (rentals.return_date is null or rentals.return_date>NOW()  or (rentals.return_date>:rentDate and rentals.rent_date< :returnDate)) " +
            "where maintains.id is null and rentals.id is null ",nativeQuery = true)
    List<Car> getCarBySearch(@Param("rentDate") LocalDate rentDate, @Param("returnDate") LocalDate returnDate);
}
