package com.servicebuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service extends AbstractEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "cost")
    @NotNull
    private double cost;

    @Column(name = "execution_time")
    @NotNull
    private long executionTimeMillis;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> order = new ArrayList<>();

    @ManyToMany(mappedBy = "services")
    private List<Master> masters;


}
