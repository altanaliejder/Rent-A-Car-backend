package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionDao extends JpaRepository<Promotion,Integer> {
    Promotion getPromotionById(int id);
    Promotion getPromotionByCode(String code);
}
