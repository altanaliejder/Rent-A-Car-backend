package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Payment;

public interface PaymentService {
    DataResult<Payment> add(CreatePaymentRequest createPaymentRequest);
    DataResult<Payment> getPaymentByRentalId(int rentalId);
    DataResult<Double> getTotalPrice(int rentalId,String promotionCode);
}
