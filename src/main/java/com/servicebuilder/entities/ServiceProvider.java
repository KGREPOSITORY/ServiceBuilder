package com.servicebuilder.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_provider")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceProvider extends AbstractEntity {

    @Column(name = "serviceID")
    @NotNull
    private long serviceID;

    @Column(name = "masterID")
    @NotNull
    private long masterID;
}
