package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Promotion;

public interface PromotionService {
    DataResult<Promotion> getById(int id);
    DataResult<Promotion> getByCode(String code);
    Result add(Promotion promotion);
}
