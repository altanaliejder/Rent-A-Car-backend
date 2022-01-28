package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.*;
import com.btkakademi.rentacar.business.dtos.IndividualInvoiceListDto;
import com.btkakademi.rentacar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.InvoiceDao;
import com.btkakademi.rentacar.entities.concretes.Invoice;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;
@Service
public class InvoiceManager implements InvoiceService {
    private RentalService rentalService;
    private InvoiceDao invoiceDao;
    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private ModelMapperService modelMapperService;
    private PaymentService paymentService;

    public InvoiceManager(RentalService rentalService, InvoiceDao invoiceDao, IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService, ModelMapperService modelMapperService, @Lazy PaymentService paymentService) {
        this.rentalService = rentalService;
        this.invoiceDao = invoiceDao;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.modelMapperService = modelMapperService;
        this.paymentService = paymentService;
    }

    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {
        var invoice=modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        var response= BusinessRules.run(checkIfInvoiceExist(createInvoiceRequest.getRentalId()));
        if(response==null){
            invoice.setDate(LocalDate.now());
            this.invoiceDao.save(invoice);
            return new SuccessResult();
        }return new ErrorResult(response.getMessage());

    }

    @Override
    public DataResult<IndividualInvoiceListDto> getIndividualList(int rentalId) {
        var rental=this.rentalService.getRentalById(rentalId).getData();
        var invoice =this.invoiceDao.getInvoiceByRentalId(rentalId);
        var customer= rental.getCustomer();
        var car=rental.getCar();
        var payment=this.paymentService.getPaymentByRentalId(rentalId).getData();
        var individual=this.individualCustomerService.getIndividualById(customer.getId()).getData();
        var additional=rental.getRentalAdditionalServices().stream().map(rentalAdditionalService -> rentalAdditionalService.getAdditionalService().getName()).collect(Collectors.toList());
        IndividualInvoiceListDto individualInvoiceListDto=new IndividualInvoiceListDto();
        individualInvoiceListDto.setBrand(car.getBrand().getName());
        individualInvoiceListDto.setColor(car.getColor().getName());
        individualInvoiceListDto.setAdditionalService(additional);
        individualInvoiceListDto.setCustomerName(individual.getFirstName());
        individualInvoiceListDto.setCustomerSurname(individual.getLastName());
        individualInvoiceListDto.setDailyPrice(car.getDailyPrice());
        individualInvoiceListDto.setCarDesciption(car.getDescription());
        individualInvoiceListDto.setTotalPrice(payment.getPrice());
        individualInvoiceListDto.setIdentityNumber(individual.getIdentityNumber());
        individualInvoiceListDto.setRentDate(rental.getRentDate());
        individualInvoiceListDto.setReturnDate(rental.getReturnDate());
        return new SuccessDataResult<IndividualInvoiceListDto>(individualInvoiceListDto);
    }

    private Result checkIfInvoiceExist(int rentalId){
        var invoice=this.invoiceDao.getInvoiceByRentalId(rentalId);
        if(invoice==null){
            return new SuccessResult();
        }return new ErrorResult("Zaten var olan fatura");
    }
}
