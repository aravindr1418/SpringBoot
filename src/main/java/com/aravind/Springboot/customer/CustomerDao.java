package com.aravind.Springboot.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer>selectAllCusotmers();
    Optional<Customer>selectCusotmerById(Integer id);
    void insertcustomer(Customer customer);
    boolean existsPersonWithEmail(String email);
    boolean existsPersonWithId(Integer id);
    void deleteCustomerById(Integer customerId);
    void updateCustomer (Customer update);
}
