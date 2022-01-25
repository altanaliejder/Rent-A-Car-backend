package com.btkakademi.rentacar.core.utilities.fakeServices;

public class FakeIndividualFindexService {
    //650-1900
    public int getIndividualCustomerFindexScore(String identity){

        int findex= (int) (Math.random()*1350+550);
        return findex;
    }
}
