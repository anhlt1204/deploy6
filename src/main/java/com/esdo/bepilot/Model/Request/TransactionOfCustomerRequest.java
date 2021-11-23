package com.esdo.bepilot.Model.Request;

import com.esdo.bepilot.Model.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOfCustomerRequest {

    private BigDecimal amount ;

    private BigDecimal MoneyRemaining ;

    private Timestamp createdAt  ;

    private Timestamp updatedAt ;

    private String status ;

    private String type ;

    private Long customerId ;
}
