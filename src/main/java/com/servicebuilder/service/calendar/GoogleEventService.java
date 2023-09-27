package com.servicebuilder.service.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import com.servicebuilder.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "GoogleEventService")
public class GoogleEventService implements EventService {

    @Value("${google.calendar.timezone}")
    private String timeZone;

    private final com.google.api.services.calendar.Calendar calendarService;
    private final GoogleCalendarService googleCalendarService;

    @Autowired
    public GoogleEventService(Calendar calendar, GoogleCalendarService googleCalendarService) {
        this.calendarService = calendar;
        this.googleCalendarService = googleCalendarService;

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

    public Order createEventForOrder(Order order) {
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
    //I don't know how to simplify it
    public Map<String, FreeBusyCalendar> getFreeTimeSpecifiedTime(DateTime startDate, DateTime finishDate) {
        FreeBusyRequest freeBusyRequestInfo = new FreeBusyRequest();
        freeBusyRequestInfo.setTimeMin(startDate);
        freeBusyRequestInfo.setTimeMax(finishDate);
        freeBusyRequestInfo.setTimeZone(timeZone);

        List<FreeBusyRequestItem> items = new ArrayList<>();
        CalendarList calendarList = googleCalendarService.getCalendarList();

        for (int i = 1; calendarList.getItems().size() - 1 >= i; i++) {
            items.add(new FreeBusyRequestItem()
                    .setId(calendarList.getItems().get(i).getId()));
        }
        freeBusyRequestInfo.setItems(items);
        Calendar.Freebusy.Query freeBusyRequest = null;
        FreeBusyResponse response = null;
        try {
            freeBusyRequest = calendarService.freebusy().query(freeBusyRequestInfo);
            response = freeBusyRequest.execute();
        } catch (IOException e) {
            throw new RuntimeException(e); //tbd
        }
        return response.getCalendars();
    }
}
