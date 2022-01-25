package com.btkakademi.rentacar.adapters.corporateFindex;

import com.btkakademi.rentacar.core.utilities.fakeServices.FakeCorporateFindexScore;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;

public class CorporateFindexServiceAdapter implements CorporateFindexService{
    @Override
    public DataResult<Integer> getFindexScoreByCorporate(String taxNumber) {
        FakeCorporateFindexScore fakeCorporateFindexScore=new FakeCorporateFindexScore();
        return new SuccessDataResult<Integer>(fakeCorporateFindexScore.getCorporateCustomerFindexScore(taxNumber));
    }
}
