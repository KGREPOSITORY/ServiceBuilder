package com.servicebuilder.service;

import com.servicebuilder.entities.Service;
import com.servicebuilder.exception.EntityAlreadyExistException;
import com.servicebuilder.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService extends AbstractService<Service, ServiceRepository> {

    public ServiceService(ServiceRepository repository) {
        super(repository);
    }

    @Override
    public void save(Service entity) {
        if (!repository.isServiceExist(entity)) {
            super.save(entity);
        }else {
            throw new EntityAlreadyExistException("Service with "+entity.getName()+" already exist");
        }
    }
}
