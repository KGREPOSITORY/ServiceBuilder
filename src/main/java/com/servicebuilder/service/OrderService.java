package com.servicebuilder.service;

import com.servicebuilder.entities.Order;
import com.servicebuilder.repository.OrderRepository;
import com.servicebuilder.service.calendar.GoogleEventService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order, OrderRepository> {

    private final GoogleEventService googleEventService;
    private final EntityManager entityManager;

    @Autowired
    public OrderService(OrderRepository repository, GoogleEventService googleEventService, EntityManager entityManager) {
        super(repository, entityManager);
        this.googleEventService = googleEventService;
        this.entityManager = entityManager;
    }

    @Override
    public Order save(Order entity) {
        entity = super.save(entity);
        entityManager.refresh(entity);
        super.save(googleEventService.createEventForOrder(entity));
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
}
