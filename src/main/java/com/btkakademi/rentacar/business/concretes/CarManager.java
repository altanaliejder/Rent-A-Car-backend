package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CarService;
import com.btkakademi.rentacar.business.constants.Messages;
import com.btkakademi.rentacar.business.dtos.CarDetailsDto;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.business.requests.carRequests.CreateCarRequest;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.CarDao;
import com.btkakademi.rentacar.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CarListDto>> getAll() {
        List<Car> carList= this.carDao.findAll();
        List<CarListDto> response=carList.stream().map(car -> modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response);
    }

    @Override
    public Result add(CreateCarRequest createCarRequest) {
        Result result= BusinessRules.run();

        if(result!=null){
            return result;
        }
        var car=modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(Messages.CAR_ADDED);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Car car= modelMapperService.forRequest().map(updateCarRequest,Car.class);
        var response=BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));
        System.out.println("sadsadsa"+response);
        if(response==null){
            this.carDao.save(car);
            return new SuccessResult();
        }return new ErrorResult();
    }

    @Override

     public DataResult<List<CarListDto>> getAll (int pageNo,  int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        List<Car> carList = this.carDao.findAll(pageable).getContent();
        List<CarListDto> response=carList.stream().map(car -> modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response);
    }

    @Override
    public DataResult<List<CarListDto>> getAll(int pageNo) {
        return this.getAll(pageNo,2);
    }

    @Override
    public DataResult<CarListDto> getCarById(int id) {
        var car=this.carDao.getCarById(id);
        CarListDto dto= this.modelMapperService.forDto().map(car,CarListDto.class);
        return new SuccessDataResult<CarListDto>(dto);
    }

    @Override
    public DataResult<List<Car>> getCarsByCarClasses(int carClassId) {
        return new SuccessDataResult<List<Car>>(this.carDao.getCarsByCarClassId(carClassId));
    }

    @Override
    public DataResult<Car> getAvailableCarsBySegment(int segmentId) {
        return new SuccessDataResult<Car>(this.carDao.getAvailableCarsBySegment(segmentId));
    }

    @Override
    public DataResult<CarDetailsDto> getCarDetails(int carId) {
        var car= this.carDao.getCarById(carId);
        var color=car.getColor();
        var brand=car.getBrand();
        CarDetailsDto carDetail=new CarDetailsDto();
        carDetail.setBrandName(brand.getName());
        carDetail.setColorName(color.getName());
        carDetail.setDailyPrice(car.getDailyPrice());
        carDetail.setDescription(car.getDescription());
        carDetail.setId(car.getId());
        carDetail.setFindexScore(car.getFindexScore());
        carDetail.setKilometer(car.getKilometer());
        carDetail.setModelYear(car.getModelYear());

        return new SuccessDataResult<CarDetailsDto>(carDetail) ;
    }

    @Override
    public DataResult<List<CarListDto>> getCarsBySearch(LocalDate rentDate, LocalDate returnDate) {
        var cars=this.carDao.getCarBySearch(rentDate,returnDate);
        var searchedCars=cars.stream().map(car -> this.modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(searchedCars);
    }

    private Result checkIfCarIdExists(int carId){
        Car car= this.carDao.getCarById(carId);
        if(car!=null){
            return new SuccessResult();
        }return new ErrorResult("Böyle bir kullanıcı yok");
    }
}
