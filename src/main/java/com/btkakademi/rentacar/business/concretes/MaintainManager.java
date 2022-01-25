package com.btkakademi.rentacar.business.concretes;
import com.btkakademi.rentacar.business.abstracts.MaintainService;
import com.btkakademi.rentacar.business.abstracts.RentalService;
import com.btkakademi.rentacar.business.dtos.MaintainListDto;
import com.btkakademi.rentacar.business.requests.maintainRequests.CreateMaintainRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.MaintainDao;
import com.btkakademi.rentacar.entities.concretes.Maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintainManager implements MaintainService {
    private MaintainDao maintainDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    @Autowired
    public MaintainManager(MaintainDao maintainDao, ModelMapperService modelMapperService, @Lazy RentalService rentalService) {
        this.maintainDao = maintainDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
    }

    @Override
    public Result add(CreateMaintainRequest createMaintainRequest) {
        var maintain=this.modelMapperService.forRequest().map(createMaintainRequest, Maintain.class);
        var respone= BusinessRules.run(checkIfCarIsRental(createMaintainRequest.getCarId()),checkIfCarIsMaintenance(createMaintainRequest.getCarId()));
        if(respone==null){
            this.maintainDao.save(maintain);
            return new SuccessResult();
        }
        return new ErrorResult(respone.getMessage());


    }

    @Override
    public DataResult<List<Maintain>> getAll(MaintainListDto maintainListDto) {
        var maintainList=this.maintainDao.findAll();
        var maintainDto=maintainList.stream().map(maintain -> this.modelMapperService.forDto().map(maintain,Maintain.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<Maintain>>(maintainDto);
    }

    @Override
    public DataResult<List<Maintain>> getMaintainByCarId(int carId) {
        return new SuccessDataResult<List<Maintain>>(this.maintainDao.getMaintainByCarId(carId));
    }

    @Override
    public DataResult<List<Maintain>> getMaintanenceCar(int carId) {
        return new SuccessDataResult<List<Maintain>>(this.maintainDao.getMaintanenceCar(carId));
    }


    private Result checkIfCarIsRental(int carId){
        var result=this.rentalService.checkRentalCar(carId);
        if (result.isSuccess()){
            return new SuccessResult();
        }return new ErrorResult("Kiradaki araba bakıma verilemez");
    }

    private Result checkIfCarIsMaintenance(int carId){
        var result=this.maintainDao.getMaintanenceCar(carId).size();
        if(result==0){
            return new SuccessResult();
        }return new ErrorResult("Araç zaten bakımda");
    }
}
