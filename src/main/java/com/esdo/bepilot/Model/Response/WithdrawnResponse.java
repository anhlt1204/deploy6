package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawnResponse {
    private Long id ;


    private Long userId ;

    private BigDecimal amount ;

    private BigDecimal moneyRemaining ;

    private OffsetDateTime createdAt ;

    private OffsetDateTime updatedAt ;

    private String status ;
}
