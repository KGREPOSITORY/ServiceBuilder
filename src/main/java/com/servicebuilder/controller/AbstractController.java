package com.servicebuilder.controller;

import com.servicebuilder.entities.AbstractEntity;
import com.servicebuilder.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AbstractController<E extends AbstractEntity,
        S extends CommonService<E>>
        implements CommonController<E> {

    protected final S service;

    @Autowired
    public AbstractController(S service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<E> createEntity(E entity) {
        service.save(entity);
        return new ResponseEntity<E>(entity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<E> getEntityById(long id) {
        E entity = service.getById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<E> deleteEntityById(long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<E> updateEntity(E entity) {
        service.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<E> updateEntityById(E entity, long id) {
        service.updateEntityById(id, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
