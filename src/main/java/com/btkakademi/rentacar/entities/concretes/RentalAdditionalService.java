package com.btkakademi.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental_additional_services")
public class RentalAdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "additional_id")
    private AdditionalService additionalService;
}
