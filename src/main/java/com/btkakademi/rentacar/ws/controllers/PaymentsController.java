package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.PaymentService;
import com.btkakademi.rentacar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private PaymentService paymentService;
    private ModelMapperService modelMapperService;
    @Autowired
    public PaymentsController(PaymentService paymentService, ModelMapperService modelMapperService) {
        this.paymentService = paymentService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping("add")
    public DataResult<Payment> add(@RequestBody CreatePaymentRequest createPaymentRequest){
        System.out.println(createPaymentRequest);
        return this.paymentService.add(createPaymentRequest);
    }
    @GetMapping("gettotalprice")
    public DataResult<Double> getTotalPrice(@RequestParam int rentalId,@RequestParam(required = false) String code){
        System.out.println("controller girdii");
        return this.paymentService.getTotalPrice(rentalId, code);
    }
}
