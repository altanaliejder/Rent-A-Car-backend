package com.btkakademi.rentacar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User{
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "customer")
    private List<CreditCard> creditCards;

}


//1. daha önce kayıtlı email kayıt olamaz;
//2. doğum tarihi 18den büyük olmalı