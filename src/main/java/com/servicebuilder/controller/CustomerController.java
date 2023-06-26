package com.servicebuilder.controller;

import com.servicebuilder.entities.Customer;
import com.servicebuilder.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController extends AbstractController<Customer, CustomerService> {

    public CustomerController(CustomerService service) {
        super(service);
    }
}
