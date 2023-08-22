package com.servicebuilder.service.calendar;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;

public interface CalendarService {

    Calendar createCalendar(Calendar calendar);
    Calendar getCalendar(String calendarId);
    CalendarList getCalendarList();
    void deleteCalendar(String calendarID);
}
