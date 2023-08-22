package com.servicebuilder.controller;

import com.servicebuilder.entities.AbstractEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommonController<E extends AbstractEntity> {

    @PostMapping
    ResponseEntity<E> createEntity(@Valid @RequestBody E entity);

    @GetMapping("/id={id}")
    ResponseEntity<E> getEntityById(@PathVariable long id);

    @GetMapping
    ResponseEntity<List<E>> getAll();

    @DeleteMapping("/id={id}")
    ResponseEntity<E> deleteEntityById(@PathVariable long id);

    @PatchMapping
    ResponseEntity<E> updateEntity(@RequestBody E entity);

    @PatchMapping("/id={id}")
    ResponseEntity<E> updateEntityById(@RequestBody E entity, @PathVariable long id);


}
