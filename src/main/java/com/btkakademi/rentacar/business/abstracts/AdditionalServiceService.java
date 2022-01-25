package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.AdditionalServiceListDto;
import com.btkakademi.rentacar.business.requests.additionalRequests.CreateAdditionalRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.dataAccess.abstratcs.AdditionalServiceDao;
import com.btkakademi.rentacar.entities.concretes.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {
    Result add(CreateAdditionalRequest createAdditionalRequest);
    DataResult<List<AdditionalServiceListDto>> getAll();
}
