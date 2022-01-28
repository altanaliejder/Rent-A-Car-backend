package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.UserService;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.UserDao;
import com.btkakademi.rentacar.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<User> getById(int id) {
        var result= BusinessRules.run(checkIfIdExist(id));
        if(result==null){
            return new SuccessDataResult<User>(this.userDao.getById(id));
        }
        return new ErrorDataResult<>(result.getMessage());
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        var result=BusinessRules.run(checkIfEmailExist(email));
        if(result==null){
            return new SuccessDataResult<User>(this.userDao.getUserByEmail(email));
        }return new ErrorDataResult<>(result.getMessage());
    }

    private Result checkIfIdExist(int id){
        var user= this.userDao.getUserById(id);
        if(user==null){
            return new ErrorResult("Kullanıcı Bulunamadı.");
        }return new SuccessResult();
    }

    private Result checkIfEmailExist(String email){
        var user= this.userDao.getUserByEmail(email);
        if(user==null){
            return new ErrorResult("Kullanıcı Bulunamadı");
        }return new SuccessResult();
    }
}
