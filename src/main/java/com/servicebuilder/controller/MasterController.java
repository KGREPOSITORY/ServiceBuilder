package com.servicebuilder.controller;

import com.servicebuilder.entities.Master;
import com.servicebuilder.service.MasterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/master")
public class MasterController extends AbstractController<Master, MasterService> {

    public MasterController(MasterService service) {
        super(service);
    }
}
