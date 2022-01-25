package com.btkakademi.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "holder")
    private String holder;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "expiration_date")
    private String expirationDate;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private Customer customer;
}
