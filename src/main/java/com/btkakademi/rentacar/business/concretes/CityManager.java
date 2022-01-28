package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CityService;
import com.btkakademi.rentacar.business.dtos.CityListDto;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CityDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {
        var cities=this.cityDao.findAll();
        var cityDto=cities.stream().map(city -> modelMapperService.forDto().map(city,CityListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CityListDto>>(cityDto);
    }
}
