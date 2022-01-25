package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CreditCardService;
import com.btkakademi.rentacar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardsController {
    private CreditCardService creditCardService;

    public CreditCardsController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateCreditCardRequest createCreditCardRequest){
        return this.creditCardService.add(createCreditCardRequest);
    }
}
