package com.servicebuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
public class AbstractEntity {
    @Transient
    @JsonIgnore
    protected String phoneValidPattern;

    @Transient
    @JsonIgnore
    protected String phoneValidationMessage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
