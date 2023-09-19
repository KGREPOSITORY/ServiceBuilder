package com.servicebuilder.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @Column(name = "eventID")
    @JsonIgnore
    private String eventID;

    @ManyToOne
    @JoinColumn(name = "customerID",
            insertable = false,
            updatable = false)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceID",
            insertable = false,
            updatable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "masterID",
            insertable = false,
            updatable = false)
    private Master master;

    public String getDescription() {
        return String.format("Description: \n" +
                        "   Customer : \n" +
                        "       Name : %s \n" +
                        "       Last name : %s \n" +
                        "       Phone number : %s \n" +
                        "   Master : \n" +
                        "       Name : %s \n" +
                        "       Lat name : %s \n" +
                        "   Service: \n" +
                        "       Name : %s \n" +
                        "       Cost : %s \n",
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                master.getFirstName(),
                master.getLastName(),
                service.getName(),
                service.getCost());
    }
}
