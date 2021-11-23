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
@Table(name = "withdrawn")
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user ;

    private BigDecimal amount ;

    private BigDecimal moneyRemaining ;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private Timestamp createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private Timestamp updatedAt;

    private String status ;

}
