package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.MissionDetailRequest;
import com.esdo.bepilot.Model.Request.TransactionOfCustomerRequest;
import com.esdo.bepilot.Service.Implement.TransactionServiceImpl;
import com.esdo.bepilot.Service.MissionDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class MissionDetailController {

    @Autowired
    MissionDetailService missionDetailService ;

    @PostMapping(value = "/missiondetails/add")
    public String createUser(@RequestBody MissionDetailRequest request) {
        log.info("Inside createUser of MissionDetailController ");
        missionDetailService.create(request);
        return "Created Complete";
    }
}