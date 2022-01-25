package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CorporateCustomerService;
import com.btkakademi.rentacar.business.requests.customerRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;

    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }

    @PostMapping("add")
    Result add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest){
        return this.corporateCustomerService.add(createCorporateCustomerRequest);
    }
}
