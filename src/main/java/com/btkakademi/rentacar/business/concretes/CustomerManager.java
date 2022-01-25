package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CustomerService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CustomerDao;
import com.btkakademi.rentacar.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {
    private CustomerDao customerDao;
    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    @Override
    public DataResult<Customer> getCustomerById(int id) {

        return new SuccessDataResult<Customer>(this.customerDao.getCustomerById(id));
    }
}
