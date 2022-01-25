package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.PromotionService;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.PromotionDao;
import com.btkakademi.rentacar.entities.concretes.Promotion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PromotionManager implements PromotionService {
    private PromotionDao promotionDao;

    public PromotionManager(PromotionDao promotionDao) {
        this.promotionDao = promotionDao;
    }
    //private P

    @Override
    public DataResult<Promotion> getById(int id) {
        return new SuccessDataResult<Promotion>(this.promotionDao.getById(id));
    }

    @Override
    public DataResult<Promotion> getByCode(String code) {
        return new SuccessDataResult<Promotion>(this.promotionDao.getPromotionByCode(code));
    }

    @Override
    public Result add(Promotion promotion) {
        var response=BusinessRules.run(checkDate(promotion.getPromotionDate(),promotion.getPromotionEndDate()),checkIfCodeExist(promotion.getCode()));
        if(response==null){
            this.promotionDao.save(promotion);
            return new SuccessResult();
        }return new ErrorResult(response.getMessage());

    }

    private Result checkDate(LocalDate codeDate,LocalDate endDate){
        if(endDate.isAfter(codeDate)){
            return new ErrorResult("Code süresi geçmiş");
        }return new SuccessResult();
    }
    private Result checkIfCodeExist(String code){
        var promotion=promotionDao.getPromotionByCode(code);
        if(promotion==null){
            return new ErrorResult("Zaten kayıtlı kod");
        }return new SuccessResult();
    }
}
