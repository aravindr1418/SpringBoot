package com.aravind.Springboot.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existCustomerByEmail(String email);
}
