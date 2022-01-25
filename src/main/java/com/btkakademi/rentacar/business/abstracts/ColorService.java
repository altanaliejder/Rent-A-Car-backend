package com.btkakademi.rentacar.business.abstracts;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.business.requests.colorRequests.CreateColorRequest;
import com.btkakademi.rentacar.business.requests.colorRequests.UpdateColorRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;

import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();
    Result add(CreateColorRequest createColorRequest);
    Result update(UpdateColorRequest updateColorRequest);
}
