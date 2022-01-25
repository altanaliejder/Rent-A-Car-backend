package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CorporateCustomerService;
import com.btkakademi.rentacar.business.requests.customerRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.CorporateCustomerDao;
import com.btkakademi.rentacar.entities.concretes.CorporateCustomer;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        var corporateCustomer=this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        var response= BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()),checkIfEmailExists(createCorporateCustomerRequest.getEmail()));
        if(response==null){
            this.corporateCustomerDao.save(corporateCustomer);
            return new SuccessResult();
        }return new ErrorResult();

    }

    @Override
    public DataResult<CorporateCustomer> getCorporateByTaxNumber(String identityNumber) {
        return new SuccessDataResult<CorporateCustomer>(this.corporateCustomerDao.getCorporateCustomerByTaxNumber(identityNumber));
    }

    @Override
    public DataResult<CorporateCustomer> getByCorporateId(int id) {
        return new SuccessDataResult<CorporateCustomer>(this.corporateCustomerDao.getById(id));
    }

    private Result checkIfEmailExists(String email){
        var corporateCustomer=this.corporateCustomerDao.getCorporateCustomerByEmail(email);
        if(corporateCustomer==null){
            return new SuccessResult();
        }return new ErrorResult();
    }
    private Result checkIfCompanyNameExists(String companyName){
        var corporateCustomer=this.corporateCustomerDao.getCorporateCustomerByCompanyName(companyName);
        if(corporateCustomer==null){
            return new SuccessResult();

        }return  new ErrorResult();
    }

}
