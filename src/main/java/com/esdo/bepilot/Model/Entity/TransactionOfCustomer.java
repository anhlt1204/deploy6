package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "transaction_of_customer")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOfCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id ;

    private BigDecimal amount ;

    private BigDecimal MoneyRemaining ;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private Timestamp createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private Timestamp updatedAt;

    private String status ;

    private String type ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer ;


}
