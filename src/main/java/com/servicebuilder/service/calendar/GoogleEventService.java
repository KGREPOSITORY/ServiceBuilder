package com.servicebuilder.service.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleEventService implements EventService {

    private final com.google.api.services.calendar.Calendar calendarService;

    @Autowired
    public GoogleEventService(Calendar calendar) {
        this.calendarService = calendar;
    }

    @Override
    public Event createEvent(String calendarID, Event event) {
        try {
            return calendarService.events().insert(calendarID, event).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null; //tbd
    }

    @Override
    public void deleteEvent(String calendarID, String eventID) {
        try {
            calendarService.events().delete(calendarID, eventID).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
    }

    @Override
    public Event updateEvent(String calendarID, String eventID, Event event) {
        try {
            return calendarService.events().update(calendarID, eventID, event).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null; //tbd
    }

    @Override
    public List<Event> getAllEvents(String calendarID) {
        String pageToken = null;
        try {
            return calendarService
                    .events()
                    .list(calendarID)
                    .setPageToken(pageToken)
                    .execute()
                    .getItems();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null; //tbd
    }

    @Override
    public Event getEvent(String calendarID, String eventID) {
        try {
            return calendarService.events().get(calendarID, eventID).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null; //tbd
    }
}
