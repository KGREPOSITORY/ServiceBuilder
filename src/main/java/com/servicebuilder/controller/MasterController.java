package com.servicebuilder.controller;

import com.servicebuilder.entities.Master;
import com.servicebuilder.facade.MasterFacade;
import com.servicebuilder.service.MasterService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/master")
public class MasterController extends AbstractController<Master, MasterService> {

    @Autowired
    private MasterFacade masterFacade;

    public MasterController(MasterService service) {
        super(service);
    }

    @GetMapping("/availability")
    public ResponseEntity<?> getFreeMasterByTimeRange(HttpServletRequest request) {
       List<Master> masters = masterFacade.getFreeMastersByTimeRange(
                request.getParameter("startTime"),
                request.getParameter("finishTime"));
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }
}
