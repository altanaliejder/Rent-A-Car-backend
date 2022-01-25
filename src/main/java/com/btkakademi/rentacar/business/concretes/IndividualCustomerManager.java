package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.IndividualCustomerService;
import com.btkakademi.rentacar.business.requests.customerRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.IndividualCustomerDao;
import com.btkakademi.rentacar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
@Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        var individualCustomer=this.modelMapperService.forRequest().map(createIndividualCustomerRequest,IndividualCustomer.class);
        var response= BusinessRules.run(checkIfEmailExists(createIndividualCustomerRequest.getEmail()),checkAge(createIndividualCustomerRequest.getBirthDate()));
        if(response==null){
            this.individualCustomerDao.save(individualCustomer);
            return new SuccessResult();
        }
        return new ErrorResult(response.getMessage());
    }

    @Override
    public DataResult<IndividualCustomer> getIndividualById(int id) {
        return new SuccessDataResult<IndividualCustomer>(this.individualCustomerDao.getById(id));
    }

    @Override
    public DataResult<IndividualCustomer> getIndividualByIdentity(String identityNumber) {
        return new SuccessDataResult<IndividualCustomer>(this.individualCustomerDao.getIndividualCustomerByIdentityNumber(identityNumber));
    }


    private Result checkIfEmailExists(String email){
        var individualCustomer=individualCustomerDao.getIndividualCustomerByEmail(email);
        if(individualCustomer==null){
            return new SuccessResult();
        }return new ErrorResult("Tekrar eden email");

    }
    private Result checkAge(LocalDate date){
    var now = LocalDate.now();
    if(now.getYear()-date.getYear()<18){
        return new ErrorResult("Yaş 18 den küçük olamaz");
    }return new SuccessResult();
    }
}
