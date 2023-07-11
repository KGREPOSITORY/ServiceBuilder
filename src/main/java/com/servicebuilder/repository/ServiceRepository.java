package com.servicebuilder.repository;

import com.servicebuilder.entities.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CommonRepository<Service>{

    @Query("select case when (count(s.name) > 0) then true else false end " +
            "from Service s " +
            "where s.name = :#{#service.name}")
    boolean isServiceExist(Service service);
}
