package com.servicebuilder.service;

import com.servicebuilder.entities.Master;
import com.servicebuilder.repository.MasterRepository;
import org.springframework.stereotype.Service;

@Service
public class MasterService extends AbstractService<Master, MasterRepository> {

    public MasterService(MasterRepository repository) {
        super(repository);
    }
}
