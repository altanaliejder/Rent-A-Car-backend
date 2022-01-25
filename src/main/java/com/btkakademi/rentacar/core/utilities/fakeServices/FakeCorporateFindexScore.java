package com.btkakademi.rentacar.core.utilities.fakeServices;

public class FakeCorporateFindexScore {
    public int getCorporateCustomerFindexScore(String identity){

        int findex= (int) (Math.random()*1350+550);
        return findex;
    }
}
