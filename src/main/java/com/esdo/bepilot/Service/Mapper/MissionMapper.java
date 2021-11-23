package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.Mission;
import com.esdo.bepilot.Model.Enum.MissionStatus;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.MissionResponse;
import com.esdo.bepilot.Repository.CustomerRepository;
import com.esdo.bepilot.Service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MissionMapper {

    @Autowired
    MissionService missionService;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MissionDetailMapper missionDetailMapper;

    public List<MissionResponse> mapToListResponse(List<Mission> missions) {
        List<MissionResponse> missionResponses = new ArrayList<>();
        missions.forEach(mission -> missionResponses.add(mapToMissionResponse(mission)));
        return missionResponses;
    }

    /**
     * Map dữ liệu từ entity sang response
     *
     * @param mission
     * @return
     */
    public MissionResponse mapToMissionResponse(Mission mission) {
        MissionResponse missionResponse = new MissionResponse();
        missionResponse.setMissionKey(mission.getMissionKey());
        missionResponse.setMissionType(mission.getMissionType());
        missionResponse.setCommunication(mission.getCommunication());
        missionResponse.setId(mission.getId());
        missionResponse.setKeyWord(mission.getKeyWord());
        missionResponse.setLink(mission.getLink());
        missionResponse.setName(mission.getName());
        missionResponse.setCreateAt(mission.getCreateAt());
        missionResponse.setUpdateAt(mission.getUpdateAt());
        missionResponse.setDeadTime(mission.getDeadTime());
        missionResponse.setMoneyReceived(mission.getMoneyReceived());
        missionResponse.setPriceUnit(mission.getPriceUnit());
        missionResponse.setQuantity(mission.getQuantity());
        missionResponse.setQuantityMade(mission.getQuantityMade());
        missionResponse.setStatus(mission.getStatus());
        missionResponse.setCustomerName(mission.getCustomer().getName());
        missionResponse.setCustomerId(mission.getCustomer().getId());
        missionResponse.setCompanyName(mission.getCustomer().getCompanyName());
        missionResponse.setMissionDetails(missionDetailMapper.mapToListMissionDetail(mission.getMissionDetails()));
        return missionResponse;
    }

    /**
     * Map dữ liệu từ request sang entity
     *
     * @param missionRequest
     * @return
     */
    public Mission mapToMission(MissionRequest missionRequest) {
        Mission mission = new Mission();
        mission.setCustomer(missionService.findCustomerById(missionRequest.getCustomerId()));
        mission.setId(missionRequest.getId());
        mission.setStatus(MissionStatus.getRandom().name());
        mission.setDeadTime(7);
        mission.setMissionType(missionRequest.getMissionType());
        mission.setCommunication(missionRequest.getCommunication());
        mission.setKeyWord(missionRequest.getKeyWord());
        mission.setLink(missionRequest.getLink());
        mission.setName(missionRequest.getName());
        mission.setMoneyReceived(BigDecimal.valueOf(missionRequest.getPriceUnit().doubleValue() * missionRequest.getQuantity()));
        mission.setPriceUnit(missionRequest.getPriceUnit());
        mission.setQuantity(missionRequest.getQuantity());
        mission.setQuantityMade(0);
        mission.setMissionKey(missionRequest.getMissionKey());
        mission.setCreateAt(missionRequest.getCreateAt());
        return mission;
    }


}
