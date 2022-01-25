package com.btkakademi.rentacar.adapters.corporateFindex;

import com.btkakademi.rentacar.core.utilities.results.DataResult;

public interface CorporateFindexService {
    DataResult<Integer> getFindexScoreByCorporate(String taxNumber);
}
