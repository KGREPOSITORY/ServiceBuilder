package com.servicebuilder.controller;

import com.servicebuilder.entities.Service;
import com.servicebuilder.service.OrderService;
import com.servicebuilder.service.ServiceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/service")
public class ServiceController extends AbstractController<Service, ServiceService> {

    public ServiceController(ServiceService service) {
        super(service);
    }
}
