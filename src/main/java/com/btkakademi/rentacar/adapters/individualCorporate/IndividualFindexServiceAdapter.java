package com.btkakademi.rentacar.adapters.individualCorporate;

import com.btkakademi.rentacar.core.utilities.fakeServices.FakeIndividualFindexService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;

public class IndividualFindexServiceAdapter implements IndividualFindexService {

    @Override
    public DataResult<Integer> getFindexScoreByIndividual(String identityNumber) {
        FakeIndividualFindexService findexService=new FakeIndividualFindexService();
        return new SuccessDataResult<Integer>(findexService.getIndividualCustomerFindexScore(identityNumber));
    }
}
