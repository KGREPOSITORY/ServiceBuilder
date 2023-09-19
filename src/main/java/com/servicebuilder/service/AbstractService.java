package com.servicebuilder.service;

import com.servicebuilder.customannotations.ParentTrasactionalAnnotation;
import com.servicebuilder.entities.AbstractEntity;
import com.servicebuilder.repository.CommonRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@ParentTrasactionalAnnotation
public class AbstractService<E extends AbstractEntity,
        R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;
    protected final EntityManager entityManager;

    @Autowired
    public AbstractService(R repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> getAll() {
        List<E> entities = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(entities::add);
        return entities;
    }

    @Override
    public E getById(long id) {
        return repository.findById(id).orElseThrow(); //tbd
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        repository.findById(id).orElseThrow(NoSuchElementException::new); //tbd
        repository.deleteById(id);
    }

    @Override
    public void updateEntityById(long id, E updatedEntity) {
        updatedEntity.setId(id);
        repository.save(updatedEntity);
    }
}
