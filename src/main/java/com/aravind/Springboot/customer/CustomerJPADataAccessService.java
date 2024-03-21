package com.aravind.Springboot.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> selectAllCusotmers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCusotmerById(Integer id) {
        return customerRepository.findById(id);

    }

    @Override
    public void insertcustomer(Customer customer) {
         customerRepository.save(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
       return customerRepository.existCustomerByEmail(email);

    }

}
