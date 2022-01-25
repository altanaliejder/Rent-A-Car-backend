package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.MaintainListDto;
import com.btkakademi.rentacar.business.requests.maintainRequests.CreateMaintainRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Maintain;
import org.jboss.jandex.Main;

import java.util.List;

public interface MaintainService {
    Result add(CreateMaintainRequest createMaintainRequest);
    DataResult<List<Maintain>> getAll(MaintainListDto maintainListDto);
    DataResult<List<Maintain>> getMaintainByCarId(int carId);
    DataResult<List<Maintain>> getMaintanenceCar(int carId);
}
