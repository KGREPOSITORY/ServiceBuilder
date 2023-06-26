package com.servicebuilder.service;

import com.servicebuilder.entities.Order;
import com.servicebuilder.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order, OrderRepository> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }
}
