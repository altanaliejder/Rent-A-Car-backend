package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CarClassesService;
import com.btkakademi.rentacar.business.dtos.CarClassesListDto;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CarClassesDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarClassesManager implements CarClassesService {
    private CarClassesDao carClassesDao;
    private ModelMapperService modelMapperService;

    public CarClassesManager(CarClassesDao carClassesDao, ModelMapperService modelMapperService) {
        this.carClassesDao = carClassesDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CarClassesListDto>> getAllCarClasses() {
        var carClasses= this.carClassesDao.findAll();
        List<CarClassesListDto> carClassesDto=carClasses.stream().map(carClass -> modelMapperService.forDto().map(carClass,CarClassesListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarClassesListDto>>(carClassesDto);
    }
}
