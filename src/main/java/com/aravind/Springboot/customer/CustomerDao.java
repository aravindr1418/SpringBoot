package com.aravind.Springboot.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer>selectAllCusotmers();
    Optional<Customer>selectCusotmerById(Integer id);
}
