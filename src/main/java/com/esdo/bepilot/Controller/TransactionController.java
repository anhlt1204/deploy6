package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.TransactionOfCustomerRequest;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Service.Implement.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService ;

    @PostMapping(value = "/transactions/add")
    public String createUser(@RequestBody TransactionOfCustomerRequest request) {
        log.info("Inside createUser of TransactionController ");
        transactionService.create(request);
        return "Created Complete";
    }
}
