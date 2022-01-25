package com.btkakademi.rentacar.adapters.individualCorporate;

import com.btkakademi.rentacar.core.utilities.results.DataResult;

public interface IndividualFindexService {
    DataResult<Integer> getFindexScoreByIndividual(String identityNumber);
}
