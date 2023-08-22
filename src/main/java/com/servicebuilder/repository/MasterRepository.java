package com.servicebuilder.repository;

import com.servicebuilder.entities.Master;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MasterRepository extends CommonRepository<Master> {

    @Query("select case when (count(m.firstName) > 0) then true else false end " +
            "from Master m " +
            "where m.firstName = :#{#master.firstName} " +
            "and m.lastName = :#{#master.lastName} ")
    boolean isMasterExist(Master master);

    @Modifying
    @Query("update Master m " +
            "set m.calendarID = :#{#calendarID} " +
            "where m.id = :#{#master.id}")
    void updateCalendarId(Master master,@Param("calendarID") String calendarID);
}
