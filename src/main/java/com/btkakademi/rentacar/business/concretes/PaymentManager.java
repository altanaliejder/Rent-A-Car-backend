package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.adapters.pos.PosService;
import com.btkakademi.rentacar.business.abstracts.*;
import com.btkakademi.rentacar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.PaymentDao;
import com.btkakademi.rentacar.entities.concretes.Payment;
import com.btkakademi.rentacar.entities.concretes.Promotion;
import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentManager implements PaymentService {
    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private RentalAdditionalServiceService rentalAdditionalServiceService;
    private PosService posService;
    private PromotionService promotionService;
    private InvoiceService invoiceService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService, RentalAdditionalServiceService rentalAdditionalServiceService, PosService posService, PromotionService promotionService, InvoiceService invoiceService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.rentalAdditionalServiceService = rentalAdditionalServiceService;
        this.posService = posService;

        this.promotionService = promotionService;
        this.invoiceService = invoiceService;
    }

    @Override
    public DataResult<Payment> add(CreatePaymentRequest createPaymentRequest) {
        var payment=modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        System.out.println(createPaymentRequest.getRentalId());

        double totalPrice=calculatePrice(createPaymentRequest.getRentalId(),createPaymentRequest.getCode());
        payment.setPrice(totalPrice);
        var response= BusinessRules.run(checkIfCardLimit(createPaymentRequest));
        if(response==null){
            this.paymentDao.save(payment);
            return new SuccessDataResult<Payment>(payment);
        }return new ErrorDataResult<Payment>(response.getMessage());

    }

    @Override
    public DataResult<Payment> getPaymentByRentalId(int rentalId) {
        return new SuccessDataResult<Payment>(this.paymentDao.getPaymentByRentalId(rentalId));
    }

    @Override
    public DataResult<Double> getTotalPrice(int rentalId,String promotionCode) {
        return new SuccessDataResult<Double>(this.calculatePrice(rentalId,promotionCode));
    }

    private double calculatePrice(int rentalId,String promotionCode){
        double additionalPrice=0;
        System.out.println("calculate girdi");
        Rental rental=this.rentalService.getRentalById(rentalId).getData();
        Promotion promotion=this.promotionService.getByCode(promotionCode).getData();
        System.out.println(promotion);
        double dailyPrice= rental.getCar().getDailyPrice();
        var rentDate=rental.getRentDate();
        var returnDate=rental.getReturnDate();
        var additionalService=this.rentalAdditionalServiceService.getAdditionalByRentalId(rentalId).getData();
        if(additionalService!=null||additionalService.size()!=0){
            for(var additional:additionalService){
                additionalPrice+=additional.getAdditionalService().getPrice();
            }
        }else{
            additionalPrice=0;
        }

        int numberOfDay=returnDate.getDayOfYear()-rentDate.getDayOfYear();
        if(numberOfDay==0){
            numberOfDay=1;
        }

        double totalPrice= numberOfDay*dailyPrice+additionalPrice ;
        if(promotion!=null){
            System.out.println(totalPrice);
            double discountPrice=promotion.getDiscountRate()/100.0*totalPrice;
            System.out.println(discountPrice);
            totalPrice-=discountPrice;
            System.out.println(totalPrice);
        }
        return totalPrice;
    }

    private Result checkIfCardLimit(CreatePaymentRequest createPaymentRequest) {
        if(this.posService.checkIfLimit(createPaymentRequest.getHolder(), createPaymentRequest.getCardNumber(), createPaymentRequest.getCvv(), createPaymentRequest.getExpirationDate()).isSuccess()){
            return new SuccessResult();
        }return new ErrorResult("Limit yetersiz");
    }
}
//payment yaparken kredi sakla butonu olursa ilişkili ekle
//banka servisi kredi kart bilgi girildiğinde ödeme işlemi tamamlanmadan önce kredi kart limiti yetiyorsa ödeme gerçekleşir