package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.WithdrawnRequest;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Service.Implement.WithdrawnServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class WithdrawnController {

    @Autowired
    WithdrawnServiceImpl WithdrawnService ;

    @PostMapping(value = "/withdrawns/add")
    public String createUser(@RequestBody WithdrawnRequest request) {
        log.info("Inside createUser of WithdrawnController ");
        WithdrawnService.create(request);
        return "Created Complete";
    }
}
