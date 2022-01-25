package com.btkakademi.rentacar.business.concretes;
import com.btkakademi.rentacar.business.abstracts.BrandService;
import com.btkakademi.rentacar.business.constants.Messages;
import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.BrandDao;
import com.btkakademi.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<BrandListDto>> getAll() {
        List<Brand> brandList = this.brandDao.findAll();
        List<BrandListDto> response= brandList.stream().map(brand -> modelMapperService
                .forDto()
                .map(brand,BrandListDto.class ))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<BrandListDto>>(response) ;
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        Result result= BusinessRules.run(checkIfBrandNameExists(createBrandRequest.getName()),checkIfBrandLimitExceeded(50));

        if(result!=null){
            return result;
        }
        var brand=modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        this.brandDao.save(brand);
        return new SuccessResult(Messages.BRAND_ADDED);
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Brand brand= modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
        var response=BusinessRules.run(checkIfBrandIdExists(updateBrandRequest.getId()));
        if(response==null){
            this.brandDao.save(brand);
            return new SuccessResult();
        }return new ErrorResult();

    }

    private Result checkIfBrandNameExists(String brandName) {
        Brand brand=this.brandDao.getBrandByName(brandName);
        if(brand!=null){
            return new ErrorResult(Messages.BRAND_NAME_EXISTS);
        }return new SuccessResult();
    }

    private Result checkIfBrandLimitExceeded(int limit){
        if(this.brandDao.count()>=limit){
            return new ErrorResult(Messages.BRAND_LIMIT_EXCEEDED);
        }return new SuccessResult();
    }
    private Result checkIfBrandIdExists(int brandId){
        var brand= this.brandDao.findById(brandId);
        if(brand!=null){
            return new SuccessResult();
        }return new ErrorResult("Böyle bir kullanıcı yok");
    }

}
