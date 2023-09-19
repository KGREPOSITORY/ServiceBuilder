package com.servicebuilder.service;

import com.servicebuilder.entities.Service;
import com.servicebuilder.exception.EntityAlreadyExistException;
import com.servicebuilder.repository.ServiceRepository;
import jakarta.persistence.EntityManager;

@org.springframework.stereotype.Service
public class ServiceService extends AbstractService<Service, ServiceRepository> {

    public ServiceService(ServiceRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    public Service save(Service entity) {
        if (!repository.isServiceExist(entity)) {
            return super.save(entity);
        } else {
            throw new EntityAlreadyExistException("Service with " + entity.getName() + " already exist");
        }
    }
}
