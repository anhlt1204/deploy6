package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Model.Entity.*;
import com.esdo.bepilot.Model.Request.MissionDetailRequest;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import com.esdo.bepilot.Repository.MissionDetailRepository;
import com.esdo.bepilot.Repository.MissionRepository;
import com.esdo.bepilot.Repository.UserRepository;
import com.esdo.bepilot.Service.Mapper.MissionDetailMapper;
import com.esdo.bepilot.Service.Mapper.MissionMapper;
import com.esdo.bepilot.Service.MissionDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MissionDetailServiceImpl implements MissionDetailService {

    @Autowired
    public MissionDetailRepository missionDetailRepository ;

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    MissionRepository missionRepository ;

    @Autowired
    public MissionDetailMapper mapper ;

    public MissionDetail create(MissionDetailRequest request){
        log.info("Inside create of MissionDetail Service ");
        MissionDetail missionDetail = mapper.mapToMissionDetailRequest(request) ;
        User user = userRepository.findById(request.getUserId()).get() ;
        missionDetail.setUsers(user);
        user.getMissionDetailList().add(missionDetail) ;
        Mission mission = missionRepository.findById(request.getMissionId()).get() ;
        missionDetail.setMission(mission);
        mission.getMissionDetails().add(missionDetail) ;
        missionDetail.setNameMission(mission.getName());
        missionDetailRepository.save(missionDetail) ;
//        missionDetailRepository.save(missionDetail) ;
        return null ;
    }

    public List<MissionDetail> getAllMissionDetail(){
        log.info("Inside getAllMissionDetail of MissionDetail Service ");
//        missionDetailRepository.findAll() ;
        return null ;
    }

    public List<MissionDetailResponse> getMissionDetailByUserId(int pageIndex , int pageSize ,Long id) {
        log.info("Inside getMissionDetailById of MissionDetail Service ");
        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<MissionDetail> page = missionDetailRepository.getByUserId(paging,id) ;
        List<MissionDetail> list = page.getContent();
        List<MissionDetailResponse> responses = mapper.mapToListMissionDetail(list) ;
        return responses ;
    }

    public List<MissionDetailGroupByDay> getMissionDetailByDay(int pageIndex , int pageSize , Long id) {
        log.info("Inside getMissionDetailById of MissionDetail Service ");
        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<MissionDetailGroupByDay> page = missionDetailRepository.getMissionDetailGroupByDay(paging,id) ;
        List<MissionDetailGroupByDay> responses = page.getContent();
        return responses ;
    }

    public String deleteMissionDetailById(Long id) {
        log.info("Inside deleteMissionDetailById of MissionDetail Service ");
//        missionDetailRepository.deleteById(id);
        return "" ;
    }

    public MissionDetail updateMissionDetailById(MissionDetail newMissionDetail) {
        log.info("Inside updateMissionDetailById of MissionDetail Service ");
//        missionDetailRepository.save(newMissionDetail);
        return null ;
    }
}