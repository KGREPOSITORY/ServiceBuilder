package com.servicebuilder.service;

import com.servicebuilder.entities.Master;
import com.servicebuilder.exception.EntityAlreadyExistException;
import com.servicebuilder.repository.MasterRepository;
import org.springframework.stereotype.Service;

@Service
public class MasterService extends AbstractService<Master, MasterRepository> {

    public MasterService(MasterRepository repository) {
        super(repository);
    }

    @Override
    public void save(Master entity) {
        if (!repository.isMasterExist(entity)) {
            super.save(entity);
        } else {
            throw new EntityAlreadyExistException(
                    "Master with first name : " + entity.getFirstName()
                            + ", and last name " + entity.getLastName()
                            + " already exist");
        }
    }


}
