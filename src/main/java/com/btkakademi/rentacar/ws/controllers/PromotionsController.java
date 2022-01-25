package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.PromotionService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.entities.concretes.Promotion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {
    private PromotionService promotionService;

    public PromotionsController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/add")
    public Result add(Promotion promotion){
        return this.promotionService.add(promotion);
    }

    @GetMapping("getpromotionbyname")
    public DataResult<Promotion> getPromotionByName(@RequestParam String code){
        return this.promotionService.getByCode(code);
    }
}
