package com.servicebuilder.controller;

import com.servicebuilder.entities.ServiceProvider;
import com.servicebuilder.service.ServiceProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mapServiceToMaster")
public class ServiceProviderController extends AbstractController<ServiceProvider, ServiceProviderService> {

    public ServiceProviderController(ServiceProviderService service) {
        super(service);
    }
}
