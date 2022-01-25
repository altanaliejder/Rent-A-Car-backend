package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.IndividualCustomerService;
import com.btkakademi.rentacar.business.requests.customerRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {

    private IndividualCustomerService individualCustomerService;

    public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest){
        return this.individualCustomerService.add(createIndividualCustomerRequest);
    }

}
