package com.aravind.Springboot.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataServiceAccess implements CustomerDao{

    //db
    private static List<Customer> customers;
    static {
        customers = new ArrayList<>();
        Customer Aravind = new Customer(1,
                "Aravind",
                "aravind@gmail.com",
                23);
        customers.add(Aravind);
        Customer Aswin = new Customer(2,
                "Aswin",
                "Aswin@gmail.com",
                20);
        customers.add(Aswin);
    }


    @Override
    public List<Customer> selectAllCusotmers() {
        return customers;
    }

    @Override
    public Optional <Customer> selectCusotmerById(Integer id) {
        return customers.stream().
                filter(c -> c.getId().equals(id))
                .findFirst();

    }
}
