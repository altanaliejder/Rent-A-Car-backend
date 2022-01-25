package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CarClassesService;
import com.btkakademi.rentacar.business.dtos.CarClassesListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carclasses")
public class CarClassesController {
    private CarClassesService carClassesService;

    public CarClassesController(CarClassesService carClassesService) {
        this.carClassesService = carClassesService;
    }

    @GetMapping("getall")
    public DataResult<List<CarClassesListDto>> getAll(){
        return this.carClassesService.getAllCarClasses();
    }
}
