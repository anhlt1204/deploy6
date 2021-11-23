package com.esdo.bepilot.Model.Entity;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public interface MissionDetailGroupByDay {
    Date getTime() ;
    int getTotalMission() ;
    BigDecimal getTotalMoney() ;

}
