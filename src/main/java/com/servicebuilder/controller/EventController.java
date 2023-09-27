package com.servicebuilder.controller;

import com.google.api.client.util.DateTime;
import com.servicebuilder.service.calendar.GoogleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/event")
public class EventController {

    private final GoogleEventService eventService;

    @Autowired
    public EventController(GoogleEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/startTime={startDateTime}/finishtime={finishDateTime}")
    public ResponseEntity getFreeTimeForService(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                Date startDateTime,
                                                @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                Date finishDateTime) {
        return new ResponseEntity<>(eventService.getFreeTimeSpecifiedTime(new DateTime(startDateTime),
                new DateTime(finishDateTime)),
                HttpStatus.OK);
    }
}
