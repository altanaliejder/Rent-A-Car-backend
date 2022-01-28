package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CityService;
import com.btkakademi.rentacar.business.dtos.CityListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.entities.concretes.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("getall")
    public DataResult<List<CityListDto>> getAll(){
        return this.cityService.getAll();
    }
}
