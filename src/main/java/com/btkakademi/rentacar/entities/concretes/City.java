package com.btkakademi.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="city_name")
    private String cityName;

    @OneToMany(mappedBy = "pickUpCity")
    private List<Rental> pickUpCityRentals;

    @OneToMany(mappedBy = "returnCity")
    private List<Rental> returnCityRentals;
}
