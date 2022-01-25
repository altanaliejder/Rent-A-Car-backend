package com.btkakademi.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "discount_rate")
    private int discountRate;
    @Column(name = "promotion_date")
    private LocalDate promotionDate;
    @Column(name = "promotion_end_date")
    private LocalDate promotionEndDate;
    @OneToOne
    private Rental rental;
}
