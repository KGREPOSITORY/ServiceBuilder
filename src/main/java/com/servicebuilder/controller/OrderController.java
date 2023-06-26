package com.servicebuilder.controller;

import com.servicebuilder.entities.Master;
import com.servicebuilder.entities.Order;
import com.servicebuilder.service.MasterService;
import com.servicebuilder.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController extends AbstractController<Order, OrderService> {

    public OrderController(OrderService service) {
        super(service);
    }
}
