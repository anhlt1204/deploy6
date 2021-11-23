package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.ListMissionResponse;
import com.esdo.bepilot.Model.Response.MissionResponse;
import com.esdo.bepilot.Service.MissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("missions")
@Slf4j
public class MissionController {

    @Autowired
    MissionService missionService;

    @GetMapping(value = "/{id}")
    public MissionResponse getMissionById(@PathVariable Long id) {
        log.info("Inside get Mission by id of TransactionController ");
        return missionService.getById(id);
    }

    @GetMapping
    public ListMissionResponse getListCollateral(@RequestParam(value = "pageIndex", defaultValue = "1",
            required = false) int pageIndex,
                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                         required = false) int pageSize) {
        log.info("Inside get Mission of TransactionController ");
        return missionService.getListMission(pageIndex, pageSize);
    }

    @GetMapping({"/search"})
    public ListMissionResponse searchMission(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "communication", required = false) String communication,
                                             @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        log.info("Inside status of Mission of TransactionController ");
        return missionService.searchMission(name, communication, pageIndex, pageSize);

    }

    @GetMapping("/status")
    public ListMissionResponse getListMissionStatus(@RequestParam(value = "status") String status,
                                                                      @RequestParam(value = "customer") Long customerId,
                                                                      @RequestParam(value = "pageIndex", defaultValue = "1",
                                                                              required = false) int pageIndex,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10",
                                                                              required = false) int pageSize) {
        log.info("Inside status of Mission of TransactionController ");
        return missionService.findByStatus(status, customerId, pageIndex, pageSize);

    }

    @PostMapping
    public ResponseEntity<MissionResponse> createdMission(@RequestBody @Valid MissionRequest missionRequest) {
        log.info("Inside created Mission of TransactionController ");
        return ResponseEntity.ok().body(missionService.createMission(missionRequest));
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<MissionResponse> updateMission(@PathVariable(value = "id") Long missionId,
                                                         @RequestBody @Valid MissionRequest missionRequest) {
        log.info("Inside update Mission by id of TransactionController ");
        return ResponseEntity.ok().body(missionService.updateMissionById(missionId, missionRequest));
    }

    @DeleteMapping("/{missionId}")
    public void deleteMission(@PathVariable Long missionId) {
        log.info("Inside delete Mission by id TransactionController ");
        missionService.deleteMissionById(missionId);
    }
}
