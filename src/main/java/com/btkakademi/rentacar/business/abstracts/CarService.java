package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.CarDetailsDto;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.business.requests.carRequests.CreateCarRequest;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Car;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    DataResult<List<CarListDto>> getAll();
    Result add(CreateCarRequest createCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
    DataResult<List<CarListDto>> getAll(int pageNo,int pageSize);
    DataResult<List<CarListDto>> getAll(int pageNo);
    DataResult<CarListDto> getCarById(int id);
    DataResult<List<Car>> getCarsByCarClasses(int carClassId);
    DataResult<Car> getAvailableCarsBySegment(int segmentId);
    DataResult<CarDetailsDto> getCarDetails(int carId);
    DataResult<List<CarListDto>> getCarsBySearch(LocalDate rentDate, LocalDate returnDate);

}
