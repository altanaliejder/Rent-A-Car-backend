package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.RentalAdditionalServiceService;
import com.btkakademi.rentacar.business.requests.rentalAdditionalRequests.CreateRentalAdditionalServiceRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.RentalAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentaladditionals")
public class RentalAdditionalsController {
    private RentalAdditionalServiceService rentalAdditionalServiceService;
    @Autowired
    public RentalAdditionalsController(RentalAdditionalServiceService rentalAdditionalServiceService) {
        this.rentalAdditionalServiceService = rentalAdditionalServiceService;
    }


    @PostMapping("add")
    public Result add(@RequestBody CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest){
        return this.rentalAdditionalServiceService.add(createRentalAdditionalServiceRequest);
    }


    @GetMapping("getbyrentalid")
    public DataResult<List<RentalAdditionalService>> getRentalAdditionalByRentalId(@RequestParam int rentalId){
        return this.rentalAdditionalServiceService.getAdditionalByRentalId(rentalId);
    }
}
