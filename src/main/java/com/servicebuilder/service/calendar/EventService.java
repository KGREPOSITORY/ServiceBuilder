package com.servicebuilder.service.calendar;

import com.google.api.services.calendar.model.Event;

import java.util.List;

public interface EventService {

    Event createEvent (String calendarID,Event event);
    void deleteEvent (String calendarID, String eventID);
    Event updateEvent (String calendarID, String eventID, Event event);
    List<Event> getAllEvents (String calendarID);
    Event getEvent (String calendarID, String eventID);
}
