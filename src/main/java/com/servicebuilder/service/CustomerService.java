package com.servicebuilder.service;

import com.servicebuilder.entities.Customer;
import com.servicebuilder.exception.EntityAlreadyExistException;
import com.servicebuilder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public void save(Customer entity) {
        if (!repository.isCustomerExist(entity)) {
            super.save(entity);
        } else {
            throw new EntityAlreadyExistException(
                    "Customer with first name : " + entity.getFirstName()
                            + ", and last name :" + entity.getLastName()
                            + " already exist");
        }
    }
}
