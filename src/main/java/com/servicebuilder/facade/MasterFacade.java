package com.servicebuilder.facade;

import com.google.api.client.util.DateTime;
import com.servicebuilder.entities.Master;
import com.servicebuilder.service.MasterService;
import com.servicebuilder.service.calendar.GoogleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterFacade {

    private final GoogleEventService googleEventService;
    private final MasterService masterService;
    @Value("${timeFormat}")
    private String timeFormat;

    @Autowired
    public MasterFacade(GoogleEventService googleEventService, MasterService masterService) {
        this.googleEventService = googleEventService;
        this.masterService = masterService;
    }

    public List<Master> getFreeMastersByTimeRange(String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        DateTime start;
        DateTime finish;

        try {
            start = new DateTime(dateFormat.parse(startTime));
            finish = new DateTime(dateFormat.parse(endTime));
        } catch (ParseException e) {
            throw new RuntimeException(e); //tbd
        }
        List<String> freeCalendarIds = googleEventService.getFreeCalendarIds(start, finish);

        return freeCalendarIds.stream()
                .map(masterService::getMastersByCalendarId)
                .collect(Collectors.toList());
    }
}
