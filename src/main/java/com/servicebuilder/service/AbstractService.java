package com.servicebuilder.service;

import com.servicebuilder.entities.AbstractEntity;
import com.servicebuilder.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AbstractService<E extends AbstractEntity,
        R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(E entity) {
        repository.save(entity);
    }

    @Override
    public List<E> getAll() {
        List<E> entities = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(entities::add);
        return entities;
    }

    @Override
    public E getById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateEntityById(long id, E updatedEntity) {
        updatedEntity.setId(id);
        repository.save(updatedEntity);
    }
}
