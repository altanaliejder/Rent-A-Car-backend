package com.btkakademi.rentacar.entities.concretes;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "rent_date")
    private LocalDate rentDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "rent_kilometer")
    private int rentKilometer;
    @Column(name = "returned_kilometer")
    private int returnedKilometer;

    @ManyToOne
    @JoinColumn(name="pick_up_city_id")
    private City pickUpCity;

    @ManyToOne
    @JoinColumn(name="return_city_id")
    private City returnCity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy="rental")
    private List<Payment> payments;
@OneToMany(mappedBy = "rental")
private List<RentalAdditionalService> rentalAdditionalServices;

    @OneToOne
    private Invoice invoice;


}
