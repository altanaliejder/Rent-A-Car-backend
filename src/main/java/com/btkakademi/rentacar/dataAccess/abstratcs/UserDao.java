package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User getUserByEmail(String email);
    User getUserById(int id);
}
