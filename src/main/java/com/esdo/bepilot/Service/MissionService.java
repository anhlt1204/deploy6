package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.Mission;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.ListMissionResponse;
import com.esdo.bepilot.Model.Response.MissionResponse;

import java.util.List;

public interface MissionService {

    MissionResponse getById(Long id);

    ListMissionResponse getListMission(Integer pageIndex, Integer pageSize);

    MissionResponse createMission(MissionRequest missionRequest);

    MissionResponse updateMissionById(Long missionId, MissionRequest missionRequest);

    ListMissionResponse searchMission(String name,
                                      String communication,
                                      int pageIndex,
                                      int pageSize);

    Customer findCustomerById(Long id);

    ListMissionResponse findByStatus(String status, Long customerId, int pageIndex, int pageSize);

    void deleteMissionById(Long missionId);

}