package com.btkakademi.rentacar.entities.concretes;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "daily_price")
    private double dailyPrice;
    @Column(name = "model_year")
    private int modelYear;
    @Column(name = "description")
    private String description;
    @Column(name = "findex_score")
    private int findexScore;
    @Column(name = "kilometer")
    private int kilometer;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    private List<Maintain> maintains;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "car")
    private List<CarDamage> carDamages;

    @ManyToOne
    @JoinColumn(name = "car_class_id")
    private CarClasses carClass;
}
//ekonomi orta prestij
//bir arabadan müsaitlik yoksa başka benzer araba kiralanır.