package com.aravind.Springboot.customer;

import com.aravind.Springboot.exception.DuplicateResourceException;
import com.aravind.Springboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService    {

    private final CustomerDao customerDao;



    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCusotmers();
    }

    public Customer getCustomers(Integer id) {
        return customerDao.selectCusotmerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer with Id [%s] is not found".formatted(id)));
    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        //check if email exists
        String email = customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email){
        throw new DuplicateResourceException("email already taken"
        );
    }
    //add
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertcustomer(customer);
    }
}
