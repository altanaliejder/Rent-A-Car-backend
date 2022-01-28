package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.CityListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<CityListDto>> getAll();
}
