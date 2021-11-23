package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Request.MissionDetailRequest;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MissionDetailMapper {

    /**
     * map list entity sang 1 list response
     * @param missionDetails
     * @return
     */
    public List<MissionDetailResponse> mapToListMissionDetail(List<MissionDetail> missionDetails){
        if(missionDetails == null) {
            return null ;
        }
        List<MissionDetailResponse> responses = new ArrayList<>() ;
        missionDetails.forEach(missionDetail -> {
            responses.add(this.mapToMissionDetailResponse(missionDetail)) ;
        });
        return responses ;
    }

    /**
     * map dữ liệu từ entity sang response
     * @param missionDetail
     * @return
     */

    public MissionDetailResponse mapToMissionDetailResponse(MissionDetail missionDetail) {
        MissionDetailResponse missionDetailResponse = new MissionDetailResponse();
        missionDetailResponse.setId(missionDetail.getId());
        missionDetailResponse.setMissionId(missionDetail.getMission().getId());
        missionDetailResponse.setUserId(missionDetail.getUsers().getId());
        missionDetailResponse.setStatus(missionDetail.getStatus());
        missionDetailResponse.setCreateAt(missionDetail.getCreateAt());
        missionDetailResponse.setUpdateAt(missionDetail.getUpdateAt());
        missionDetailResponse.setNameMission(missionDetail.getNameMission());
        return missionDetailResponse;
    }

    public MissionDetail mapToMissionDetailRequest(MissionDetailRequest request) {
        MissionDetail missionDetail = new MissionDetail() ;

        missionDetail.setStatus(request.getStatus());
        return missionDetail ;
    }


}
