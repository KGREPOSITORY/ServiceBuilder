package com.servicebuilder.service.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.servicebuilder.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
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

    public Order createEventForOrder(Order order){
        Event event = new Event();
        EventDateTime startTime = new EventDateTime()
                .setDateTime(new DateTime(order.getTime()));
        EventDateTime endTime = new EventDateTime()
                .setDateTime(new DateTime(new Date(startTime
                        .getDateTime()
                        .getValue() + order.getService()
                        .getExecutionTimeMillis())));
        event.setStart(startTime);
        event.setEnd(endTime);
        event.setDescription(order.getDescription());
        event.setSummary(order.getService().getName());
        event = createEvent(order.getMaster().getCalendarID(), event);
        order.setEventID(event.getId());
        return order;
    }
}
