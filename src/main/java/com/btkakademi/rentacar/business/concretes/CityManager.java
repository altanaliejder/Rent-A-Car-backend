package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CityService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CityDao;
import com.btkakademi.rentacar.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityManager implements CityService {
    private CityDao cityDao;

    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll());
    }
}
