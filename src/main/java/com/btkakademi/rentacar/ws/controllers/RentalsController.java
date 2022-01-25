package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.RentalService;
import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    private RentalService rentalService;
@Autowired
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest){
        return this.rentalService.individualAdd(createRentalRequest);
    }

    @GetMapping("getbyid")
    public DataResult<Rental> getById(@RequestParam int id){
        System.out.println(id);
        return this.rentalService.getRentalById(id);
    }
}
