package com.servicebuilder.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {

    @Column(name = "customerID")
    @NotNull
    private long customerID;

    @Column(name = "masterID")
    @NotNull
    private long masterID;

    @Column(name = "serviceID")
    @NotNull
    private long serviceID;

    @Column(name = "time")
    private Date time = new Date();

    @ManyToOne
    @JoinColumn(name = "customerID",
            insertable = false,
            updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "serviceID",
            insertable = false,
            updatable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "masterID",
            insertable = false,
            updatable = false)
    private Master master;
}
