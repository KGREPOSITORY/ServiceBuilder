package com.servicebuilder.repository;

import com.servicebuilder.entities.Master;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CommonRepository<Master>{

    @Query("select case when (count(m.firstName) > 0) then true else false end " +
            "from Master m " +
            "where m.firstName = :#{#master.firstName} " +
            "and m.lastName = :#{#master.lastName} ")
    boolean isMasterExist(Master master);
}
