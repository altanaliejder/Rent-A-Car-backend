package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CreditCardService;
import com.btkakademi.rentacar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CreditCardDao;
import com.btkakademi.rentacar.entities.concretes.CreditCard;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class CreditCardManager implements CreditCardService {

    private CreditCardDao creditCardDao;
    private ModelMapperService modelMapperService;

    public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService) {
        this.creditCardDao = creditCardDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCreditCardRequest createCreditCardRequest) {
        var creditCard= this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
        this.creditCardDao.save(creditCard);
        return new SuccessResult();
    }
}
