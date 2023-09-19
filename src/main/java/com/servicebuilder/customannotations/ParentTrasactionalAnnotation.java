package com.servicebuilder.customannotations;

import jakarta.transaction.Transactional;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Transactional
public @interface ParentTrasactionalAnnotation {
}
