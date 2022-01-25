package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.AdditionalServiceService;
import com.btkakademi.rentacar.business.abstracts.RentalService;
import com.btkakademi.rentacar.business.dtos.AdditionalServiceListDto;
import com.btkakademi.rentacar.business.requests.additionalRequests.CreateAdditionalRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.AdditionalServiceDao;
import com.btkakademi.rentacar.entities.concretes.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
    private AdditionalServiceDao additionalServiceDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    @Autowired
    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService, RentalService rentalService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
    }

    @Override
    public Result add(CreateAdditionalRequest createAdditionalRequest) {
        var additionalService=modelMapperService.forRequest().map(createAdditionalRequest, AdditionalService.class);
        var response= BusinessRules.run(checkIfInRental(createAdditionalRequest.getRentalId()));
        if(response==null){
            this.additionalServiceDao.save(additionalService);
            return new SuccessResult();
        }return new ErrorResult(response.getMessage());

    }

    @Override
    public DataResult<List<AdditionalServiceListDto>> getAll() {
        var additionals=this.additionalServiceDao.findAll();
        var response=additionals.stream().map(additionalService -> this.modelMapperService.forDto().map(additionalService,AdditionalServiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
    }


    private Result checkIfInRental(int rentalId){
        var result=this.rentalService.checkIfInRental(rentalId);
        if(result.isSuccess()){
            return new SuccessResult();
        }return new ErrorResult("Kayıtlı bir kiralama yok");
    }
}
