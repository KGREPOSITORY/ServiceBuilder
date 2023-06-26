package com.servicebuilder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    @JsonIgnore
    private List<Order> order;

    @ManyToMany(mappedBy = "services")
    private List<Master> masters;


}
