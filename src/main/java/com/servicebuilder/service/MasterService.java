package com.servicebuilder.service;

import com.google.api.services.calendar.model.Calendar;
import com.servicebuilder.entities.Master;
import com.servicebuilder.exception.EntityAlreadyExistException;
import com.servicebuilder.facade.MasterFacade;
import com.servicebuilder.repository.MasterRepository;
import com.servicebuilder.service.calendar.GoogleCalendarService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MasterService extends AbstractService<Master, MasterRepository> {

    private final GoogleCalendarService googleCalendarService;

    @Autowired
    public MasterService(MasterRepository repository, GoogleCalendarService googleCalendarService, EntityManager entityManager) {
        super(repository, entityManager);
        this.googleCalendarService = googleCalendarService;
    }

    @Override
    public void delete(Master entity) {
        super.delete(entity);
        googleCalendarService.deleteCalendar(entity.getCalendarID());
    }

    @Override
    public void deleteById(long id) {
        Master master = repository.findById(id).orElseThrow(NoSuchElementException::new); //tbd
        super.deleteById(id);
        googleCalendarService.deleteCalendar(master.getCalendarID());
    }

    @Override
    public Master save(Master entity) {
        if (!repository.isMasterExist(entity)) {
            entity = super.save(entity);
            Calendar calendar = googleCalendarService.createCalendarForMaster(entity);
            repository.updateCalendarId(entity,calendar.getId());
            return entity;
        } else {
            throw new EntityAlreadyExistException(
                    "Master with first name : " + entity.getFirstName()
                            + ", and last name " + entity.getLastName()
                            + " already exist"); // tbd
        }
    }

    public Master getMastersByCalendarId(String calendarId){
        return repository.getMasterByCalendarID(calendarId);
    }
}
