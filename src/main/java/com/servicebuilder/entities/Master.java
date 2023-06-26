package com.servicebuilder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "master")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Master extends AbstractEntity {

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
    @JsonIgnore
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "service_provider",
            joinColumns = @JoinColumn(name = "masterID"),
            inverseJoinColumns = @JoinColumn(name = "serviceID"))
    @JsonIgnore
    private List<Service> services;
}
