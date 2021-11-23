package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.MissionDetailGroupByDay;
import com.esdo.bepilot.Model.Entity.User;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Request.UserRequest;
import com.esdo.bepilot.Model.Response.*;
import com.esdo.bepilot.Service.Implement.MissionDetailServiceImpl;
import com.esdo.bepilot.Service.Implement.UserServiceImpl;
import com.esdo.bepilot.Service.Implement.WithdrawnServiceImpl;
import com.esdo.bepilot.Service.MissionDetailService;
import com.esdo.bepilot.Service.Validate.UserValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    WithdrawnServiceImpl withdrawnService;

    @Autowired
    MissionDetailServiceImpl missionDetailService;

    @Autowired
    UserValidate userValidate;

    /**
     * lấy danh sách người dùng
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUser(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                         @RequestParam(value = "pageSize", defaultValue = "10",
                                                                 required = false) int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllUser of customerAPI ");
        List<UserResponse> responses = userService.getAllUser(pageIndex, pageSize);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());
        return response;
    }

    /**
     * Thêm mới một người dùng
     * @param request
     * @return
     */
    @PostMapping(value = "/users/add")
    public String createUser(@RequestBody UserRequest request) {
        log.info("Inside createUser of userAPI ");
        userValidate.validate(request);
        userService.create(request);
        return "Created Complete";
    }

    /**
     * Lấy chi tiết một người dùng theo id
     * @param id
     * @return
     */
    @GetMapping(value = "users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        log.info("Inside getUserById of userAPI ");
        return userService.getUserById(id);
    }

    /**
     * Lấy danh sách rút tiền của người dùng
     * @param id
     * @return
     */
    @GetMapping(value = "users/{id}/withdrawn")
    public ResponseEntity<List<WithdrawnResponse>> getListHistoryWithdrawn(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10",
                                                                                   required = false) int pageSize,
                                                                           @PathVariable Long id) {
        log.info("Inside getListHistoryWithdrawn of userAPI ");
        ResponseEntity<List<WithdrawnResponse>> response = new ResponseEntity<>();
        //TODO lấy danh sách withdrawn theo id người dùng
        List<WithdrawnResponse> responses = withdrawnService.getWithdrawnByUserId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    /**
     * Chỉnh sử một khách hàng
     * @param name
     * @param phone
     * @param id
     * @return
     */
    @PutMapping(value = "/users/{id}")
    public UserResponse updateUser(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "phone") String phone,
                                   @PathVariable long id) {
        log.info("Inside updateUser of userAPI ");
        return userService.updateUserById(id, name, phone);

    }

    /**
     * Xóa thông tin một người dùng
     * @param id
     * @return
     */
    @DeleteMapping(value = "/users/delete")
    public String deleteUser(@RequestParam Long id) {
        log.info("Inside deleteUser of userAPI ");
        userService.deleteUserById(id);
        return "Delete completed";
    }

    /**
     * lấy danh sách nhiệm vụ của người dùng
     * @param id
     * @return
     */
    @GetMapping(value = "users/{id}/missiondetails")
    public ResponseEntity<List<MissionDetailResponse>> getListMissionDetail(@PathVariable Long id,
                                                                            @RequestParam(name = "pageIndex",defaultValue = "0") int pageIndex,
                                                                            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getListMissionDetail of userAPI ");
        List<MissionDetailResponse> responses = missionDetailService.getMissionDetailByUserId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    /**
     * Lấy danh sách nhiệm vụ của người dùng theo từng ngày
     * @param id
     * @return
     */
    @GetMapping(value = "users/{id}/missiondetail")
    public ResponseEntity<List<MissionDetailGroupByDay>> getListMissionDetailByDay(@PathVariable Long id,
                                                                                   @RequestParam(name = "pageIndex",defaultValue = "0") int pageIndex,
                                                                                   @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getListMissionDetail of userAPI ");
        List<MissionDetailGroupByDay> responses = missionDetailService.getMissionDetailByDay(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    /**
     * tìm kiếm người dùng
     * @param pageIndex
     * @param pageSize
     * @param keyWord
     * @return
     */
    @GetMapping(value = "/users/search")
    public ResponseEntity<List<UserResponse>> searchCustomer(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                                         required = false) int pageSize,
                                                                 @RequestParam(name = "keyWord") String keyWord) {

        if (keyWord == null) {
            keyWord = "";
        }
        ResponseEntity response = new ResponseEntity();
        log.info("Inside searchCustomer of customerAPI ");
        List<UserResponse> responses = userService.searchUser(pageIndex, pageSize, keyWord);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }


}
