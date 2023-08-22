package com.servicebuilder.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.servicebuilder.entities.Order;
import com.servicebuilder.repository.OrderRepository;
import com.servicebuilder.service.calendar.GoogleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService extends AbstractService<Order, OrderRepository> {

    private final GoogleEventService googleEventService;

    @Autowired
    public OrderService(OrderRepository repository, GoogleEventService googleEventService) {
        super(repository);
        this.googleEventService = googleEventService;
    }

    @Override
    public Order save(Order entity) {
        createEvent(super.save(entity));
        return entity;
    }

    @Override
    public void delete(Order entity) {
        super.delete(entity);
        googleEventService.deleteEvent(entity.getMaster().getCalendarID(), entity.getEventID());
    }

    @Override
    public void deleteById(long id) {
        Order order = getById(id);
        super.deleteById(id);
        googleEventService.deleteEvent(order.getMaster().getCalendarID(), order.getEventID());

    }


    public Event createEvent(Order entity) {
        entity = repository.findById(entity.getId()).orElseThrow();
        Event event = new Event();
        EventDateTime startTime = new EventDateTime()
                .setDateTime(new DateTime(entity.getTime()));
        EventDateTime endTime = new EventDateTime()
                .setDateTime(new DateTime(new Date(startTime
                        .getDateTime()
                        .getValue() + entity.getService()
                        .getExecutionTimeMillis())));
        event.setStart(startTime);
        event.setEnd(endTime);
        event.setDescription(entity.getDescription());
        event.setSummary(entity.getService().getName());
        event = googleEventService.createEvent(entity.getMaster().getCalendarID(), event);
        entity.setEventID(event.getId());
        return event;
    }
}
