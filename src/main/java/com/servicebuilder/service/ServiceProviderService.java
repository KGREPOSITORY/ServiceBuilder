package com.servicebuilder.service;

import com.servicebuilder.entities.ServiceProvider;
import com.servicebuilder.repository.ServiceProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService extends AbstractService<ServiceProvider, ServiceProviderRepository> {

    public ServiceProviderService(ServiceProviderRepository repository) {
        super(repository);
    }
}
