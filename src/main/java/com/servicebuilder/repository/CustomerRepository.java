package com.servicebuilder.repository;

import com.servicebuilder.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CommonRepository<Customer>{

    @Query("select case when (count(c.firstName) > 0) then true else false end " +
            "from Customer c " +
            "where c.firstName = :#{#customer.firstName} " +
            "and c.lastName = :#{#customer.lastName} ")
    boolean isCustomerExist(@Param("customer") Customer customer);
}
