package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.RentalAdditionalServiceService;
import com.btkakademi.rentacar.business.requests.rentalAdditionalRequests.CreateRentalAdditionalServiceRequest;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.RentalAddtionalDao;
import com.btkakademi.rentacar.entities.concretes.RentalAdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentalAdditionalServiceManager implements RentalAdditionalServiceService {
    private RentalAddtionalDao rentalAddtionalDao;
    private ModelMapperService modelMapperService;

    public RentalAdditionalServiceManager(RentalAddtionalDao rentalAddtionalDao, ModelMapperService modelMapperService) {
        this.rentalAddtionalDao = rentalAddtionalDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<RentalAdditionalService>> getAdditionalByRentalId(int rentalId) {
        return new SuccessDataResult<List<RentalAdditionalService>>(this.rentalAddtionalDao.getRentalAdditionalServicesByRentalId(rentalId));
    }

    @Override
    public Result add(CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest) {
        var rentalAdditional=this.modelMapperService.forDto().map(createRentalAdditionalServiceRequest,RentalAdditionalService.class);
        this.rentalAddtionalDao.save(rentalAdditional);
        return new SuccessResult();
    }
}
