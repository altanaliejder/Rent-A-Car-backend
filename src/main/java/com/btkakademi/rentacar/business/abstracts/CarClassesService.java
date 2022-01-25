package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.CarClassesListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;

import java.util.List;

public interface CarClassesService {
    DataResult<List<CarClassesListDto>> getAllCarClasses();
}
