package com.aravind.Springboot.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    //constructor for Customer controller.
    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable(
            "customerId")Integer customerId)
    {
        return customerService.getCustomers(customerId);
    }
    @PostMapping
    public void registerCustomer(
            @RequestBody CustomerRegistrationRequest request){
            customerService.addCustomer(request);
    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId")Integer customerId){
        customerService.deleteCustomerById(customerId);
    }
    @PutMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId")Integer customerId,
            @RequestBody CustomerUpdateRequest updateRequest){
        customerService.updateCustomer(customerId,updateRequest);
    }


}
