package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CarService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("getall")
    public DataResult<List<CarListDto>> getAll(){
        return this.carService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest){
        return this.carService.add(createCarRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest){
        System.out.println(updateCarRequest.getId());
        return this.carService.update(updateCarRequest);
    }
    @GetMapping("getallbypage")
    public DataResult<List<CarListDto>> getAll(@RequestParam int pageNo, @RequestParam(defaultValue = "2") int pageSize){
        return pageSize==0 ? this.carService.getAll(pageNo) :  this.carService.getAll(pageNo, pageSize);
    }
    @GetMapping("getbyid")
    public DataResult<CarListDto> getCarById(@RequestParam int carId){
        return this.carService.getCarById(carId);
    }

    @GetMapping("getcardetails")
    public DataResult<CarDetailsDto> getCarDetails(@RequestParam int carId){
        return this.carService.getCarDetails(carId);
    }

    @GetMapping("getcarsbysearch")
    public DataResult<List<CarListDto>> getCarsBySearch(@RequestParam LocalDate rentDate,@RequestParam LocalDate returnDate){
        return this.carService.getCarsBySearch(rentDate,returnDate);
    }
}
