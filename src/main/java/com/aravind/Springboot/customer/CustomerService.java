package com.aravind.Springboot.customer;

import com.aravind.Springboot.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService    {

    private final CustomerDao customerDao;


    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCusotmers();
    }

    public Customer getCustomers(Integer id) {
        return customerDao.selectCusotmerById(id)
                .orElseThrow(() -> new ResourceNotFound("customer with Id [%s] is not found".formatted(id)));
    }

}
