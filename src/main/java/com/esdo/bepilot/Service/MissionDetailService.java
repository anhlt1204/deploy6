package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.MissionDetailGroupByDay;
import com.esdo.bepilot.Model.Request.MissionDetailRequest;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionDetailService {

    MissionDetail create(MissionDetailRequest missionDetail);

    List<MissionDetail> getAllMissionDetail();

    List<MissionDetailResponse> getMissionDetailByUserId(int pageIndex , int pageSize, Long id);

    String deleteMissionDetailById(Long id) ;

    MissionDetail updateMissionDetailById(MissionDetail newMissionDetail) ;


}
