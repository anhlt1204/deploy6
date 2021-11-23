package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.MissionDetailGroupByDay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MissionDetailRepository extends JpaRepository<MissionDetail, Long> {


    /*
    * lấy các missionDetail theo id của User
    *
    *
     */
    @Query(value = "SELECT m FROM MissionDetail m where m.users.id = ?1 ")
    Page<MissionDetail> getByUserId(Pageable pageable, Long id) ;

    @Query(nativeQuery = true ,value = "SELECT date (md.update_at) as time, COUNT(md.id) as totalMission, SUM(m.money_received) as totalMoney FROM mission_detail md JOIN mission m ON md.mission_id = m.id WHERE md.status = 'COMPLETE' and  md.user_id = ?1 GROUP By date(md.update_at)" )
    Page<MissionDetailGroupByDay> getMissionDetailGroupByDay(Pageable pageable ,Long id) ;


}

