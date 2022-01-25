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
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
}
