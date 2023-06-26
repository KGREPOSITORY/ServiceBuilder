package com.servicebuilder.service;

import com.servicebuilder.entities.Service;
import com.servicebuilder.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService extends AbstractService<Service, ServiceRepository> {

    public ServiceService(ServiceRepository repository) {
        super(repository);
    }
}
