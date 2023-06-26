package com.servicebuilder.service;

import com.servicebuilder.entities.Customer;
import com.servicebuilder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}
