package com.servicebuilder.service.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.servicebuilder.entities.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleCalendarService implements CalendarService{
    private final Calendar calendarService;
    @Value("${google.calendar.timezone}")
    private String timeZone;


    @Autowired
    public GoogleCalendarService(Calendar calendarService) {
        this.calendarService = calendarService;
    }

    @Override
    public CalendarList getCalendarList() {
        String token = null;
        CalendarList calendarList = null;
        try {
            calendarList = calendarService.calendarList().list().setPageToken(token).execute();
        } catch (IOException e) {
            System.err.println("Something go wrong with calendar list retrieving : " + e.getMessage()); //tbd
        }
        return calendarList;
    }

    @Override
    public void deleteCalendar(String calendarID) {
        try {
            calendarService.calendars().delete(calendarID).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
    }

    public com.google.api.services.calendar.model.Calendar createCalendarForMaster(Master master) {
        com.google.api.services.calendar.model.Calendar calendar =
                new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(master.getFirstName() + "_" + master.getLastName());
        calendar.setTimeZone(timeZone);
        calendar =createCalendar(calendar);
        return calendar;
    }

    @Override
    public com.google.api.services.calendar.model.Calendar createCalendar(com.google.api.services.calendar.model.Calendar calendar) {
        try {
            return calendarService.calendars().insert(calendar).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null; //tbd
    }

    @Override
    public com.google.api.services.calendar.model.Calendar getCalendar(String calendarId) {
        try {
            return calendarService.calendars().get(calendarId).execute();
        } catch (IOException e) {
            e.printStackTrace(); //tbd
        }
        return null;
    }
}
