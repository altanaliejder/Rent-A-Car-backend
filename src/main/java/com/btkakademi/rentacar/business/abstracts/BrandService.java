package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Brand;

import javax.xml.crypto.Data;
import java.util.List;

public interface BrandService {
    DataResult<List<BrandListDto>> getAll();
    Result add(CreateBrandRequest createBrandRequest);
    Result update(UpdateBrandRequest updateBrandRequest);
}
