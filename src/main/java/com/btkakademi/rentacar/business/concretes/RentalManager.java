package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.adapters.corporateFindex.CorporateFindexService;
import com.btkakademi.rentacar.adapters.individualCorporate.IndividualFindexService;
import com.btkakademi.rentacar.business.abstracts.*;
import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.RentalDao;
import com.btkakademi.rentacar.entities.concretes.Car;
import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class RentalManager implements RentalService {
    private ModelMapperService modelMapperService;
    private RentalDao rentalDao;
    private CustomerService customerService;
    private MaintainService maintainService;
    private IndividualFindexService individualFindexService;
    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private CarService carService;
    private CorporateFindexService corporateFindexService;
    private PromotionService promotionService;

    @Autowired
    public RentalManager(ModelMapperService modelMapperService, RentalDao rentalDao, CustomerService customerService, MaintainService maintainService, IndividualFindexService individualFindexService, IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService, CarService carService, CorporateFindexService corporateFindexService, PromotionService promotionService) {
        this.modelMapperService = modelMapperService;
        this.rentalDao = rentalDao;
        this.customerService = customerService;
        this.maintainService = maintainService;
        this.individualFindexService = individualFindexService;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.carService = carService;
        this.corporateFindexService = corporateFindexService;
        this.promotionService = promotionService;
    }

    @Override
    public DataResult<Rental> individualAdd(CreateRentalRequest createRentalRequest) {
        var rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        var individualCustomer=this.individualCustomerService.getIndividualById(createRentalRequest.getCustomerId()).getData();
        var response= BusinessRules.run(checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
                checkKilometer(createRentalRequest.getRentKilometer(),
                        createRentalRequest.getReturnedKilometer()),
                checkIfIndividualFindexScore(individualCustomer.getIdentityNumber(), createRentalRequest.getCarId()),
                /*checkRentalDate(createRentalRequest.getRentDate(),
                        createRentalRequest.getReturnDate()),*/
                checkIfCarIsMaintainence(createRentalRequest.getCarId()),
                checkIndividualAge(individualCustomer.getBirthDate())
                );
        var available=checkIfCarIsAvailable(rental.getCar().getId());
        if(!available.isSuccess()){
            var availableCar=this.carService.getAvailableCarsBySegment(rental.getCar().getCarClass().getId()).getData();
            rental.setCar(availableCar);
        }
        if(available==null){
            return new ErrorDataResult<>("Uygun araba yok");
        }

        if(response==null){
            this.rentalDao.save(rental);
            return new SuccessDataResult<Rental>(rental);
        }return new ErrorDataResult<>(response.getMessage());

    }
    @Override
    public Result corporateAdd(CreateRentalRequest createRentalRequest) {
        var rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        var corporateCustomer=this.corporateCustomerService.getByCorporateId(createRentalRequest.getCustomerId()).getData();

        var response= BusinessRules.run(checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
                checkKilometer(createRentalRequest.getRentKilometer(),
                        createRentalRequest.getReturnedKilometer()),
                checkIfCorporateFindexScore(corporateCustomer.getTaxNumber(),createRentalRequest.getCarId()),
                /*checkRentalDate(createRentalRequest.getRentDate(),
                        createRentalRequest.getReturnDate()),*/
                checkIfCarIsMaintainence(createRentalRequest.getCarId())
        );
        if(response==null){
            this.rentalDao.save(rental);
            return new SuccessResult();
        }return new ErrorResult(response.getMessage());
    }

    @Override
    public DataResult<Rental> getRentalsByCustomerId(int customerId) {
        return new SuccessDataResult<Rental>(this.rentalDao.getRentalsByCustomerId(customerId));
    }

    private Result checkRentalDate(LocalDate rentDate,LocalDate returnDate){
        if(rentDate.isAfter(returnDate)){
           return new ErrorResult("Kiralama tarihi dönüş tarihinden sonra olamaz");
        }return new SuccessResult();
    }
    private Result checkKilometer(int rentKilometer,int returnKilometer ){
        if(!(rentKilometer<returnKilometer)){
            return new ErrorResult("Kiralama km'si Dönüş kmden yüksek olamaz");
        }return new SuccessResult();
    }
    private Result checkIfCustomerIdExists(int id){
        var customer=this.customerService.getCustomerById(id).getData();
        if(customer==null){
            return new ErrorResult("Böyle Bir kullanıcı yok");
        }return new SuccessResult();
    }

    private Result checkIfCarIsMaintainence(int carId){
        var maintainence=this.maintainService.getMaintanenceCar(carId).getData().size();
        if(maintainence==0){
            return new SuccessResult();
        }return new ErrorResult("Araba bakımda kiralanamaz");
    }

     @Override
    public Result checkRentalCar(int carId){
        var rental=this.rentalDao.getRentalCar(carId).size();
        if(rental==0){
            return new SuccessResult();
        }return new ErrorResult();

    }

    @Override
    public DataResult<Rental> getRentalById(int rentalId) {
        return new SuccessDataResult<Rental>(this.rentalDao.getRentalById(rentalId));
    }

    @Override
    public Result checkIfInRental(int rentalId) {
        var rental=this.rentalDao.findById(rentalId).get();
        if(rental==null){
            return new ErrorResult();
        }return new SuccessResult();
    }



    private Result checkIfCorporateFindexScore(String tax,int carId){
        var car=this.carService.getCarById(carId).getData();
        int findex= this.corporateFindexService.getFindexScoreByCorporate(tax).getData();
        if(car.getFindexScore()>=findex){
            return new ErrorResult("findex score yetmiyor");
        }return new SuccessResult();
    }

    private Result checkIfIndividualFindexScore(String identity,int carId){
        var car=this.carService.getCarById(carId).getData();
        int findex= this.individualFindexService.getFindexScoreByIndividual(identity).getData();
        if(car.getFindexScore()>=findex){
            return new ErrorResult("findex score yetmiyor");
        }return new SuccessResult();
    }

    private Result checkIndividualAge(LocalDate birthDate){
        var nowDate=LocalDate.now().getYear()-birthDate.getYear();
        if(nowDate<25){
            return new ErrorResult("Yaşınız 25'ten küçük");
        }return new SuccessResult();
    }

    private Result checkIfCarIsAvailable(int carId){
        var car=checkRentalCar(carId);
        if(car.isSuccess()&& checkIfCarIsMaintainence(carId).isSuccess()){
            return new SuccessResult();
        }return new ErrorResult();
    }

    private DataResult<Car> assignCar(int carId){
        var segment=this.carService.getCarById(carId).getData();
        var carsBySegment=this.carService.getCarsByCarClasses(segment.getId()).getData();
        System.out.println("Segment"+ carsBySegment);
        for(var car:carsBySegment){
            System.out.println(car);
            if (checkIfCarIsAvailable(car.getId()).isSuccess()){
                System.out.println("if içi"+car);
                return new SuccessDataResult<Car>(car);
            }
        }return null;
    }
}
