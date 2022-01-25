package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.AdditionalServiceService;
import com.btkakademi.rentacar.business.dtos.AdditionalServiceListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.entities.concretes.AdditionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/additionalservices")
public class AdditionalServicesController {
    private AdditionalServiceService additionalServiceService;

    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }
@GetMapping("getall")
    DataResult<List<AdditionalServiceListDto>> getAll(){
        return this.additionalServiceService.getAll();
    }
}
