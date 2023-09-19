package com.servicebuilder;

import com.servicebuilder.entities.Master;
import com.servicebuilder.entities.Order;
import com.servicebuilder.entities.Service;
import com.servicebuilder.service.calendar.GoogleCalendarService;
import com.servicebuilder.service.calendar.GoogleEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceBuilderApplicationTests {

    @Autowired
    GoogleCalendarService googleCalendarService;
    @Autowired
    GoogleEventService googleEventService;
    Master master = new Master();
    Order order = new Order();
    Service service = new Service();

    @Test
    void contextLoads() {
//
//        EventDateTime start = new EventDateTime()
//                .setDateTime(new DateTime(new Date()));
//        EventDateTime end = new EventDateTime()
//                .setDateTime(new DateTime(System.currentTimeMillis() + 3600000));
//        master.setFirstName("Test1");
//        service.setName("SomeServiceName");
//        service.setCost(25.3);
//        order.setService(service);
//        Calendar calendar = new Calendar().setSummary(master.getFirstName());
//        calendar = googleCalendarService.createCalendar(calendar);
//        Event event = new Event()
//                .setStart(start)
//                .setEnd(end)
//                .setDescription(order.toString());
//
//        event = googleEventService.createEvent(calendar.getId(),event);
//        googleEventService.deleteEvent(calendar.getId(), event.getId());

    }

}
