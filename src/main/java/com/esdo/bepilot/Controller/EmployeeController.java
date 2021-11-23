package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.EmployeeRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ListEmployeeResponse;
import com.esdo.bepilot.Service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Tạo mới nhân viên
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Tạo mới nhân viên")
    EmployeeResponse createNewEmployee(@RequestBody EmployeeRequest request) {
        log.info(this.getClass().getName() +
                ": start createNewEmployee" + "\n\tParam\n\t\t" + request);
        return employeeService.createNewEmployee(request);
    }

    /**
     * Lấy danh sách nhân viên
     * @param page
     * @param size
     * @param key
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Lấy danh sách nhân viên")
    ListEmployeeResponse findAllEmployee(@RequestParam(defaultValue = "1", required = false) Integer page,
                                         @RequestParam(defaultValue = "10", required = false) Integer size,
                                         @RequestParam(required = false) String key) {
        log.info(this.getClass().getName() + ": start findAllEmployee" +
                "\n\tParam\n\t\t" + page + "\n\t\t" + size + "\n\t\t" + key);
        return employeeService.findAllEmployee(page, size, key);
    }

    /**
     * Lấy thông tin nhân viên theo id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Lấy thông tin nhân viên theo id")
    EmployeeResponse findEmployeeById(@PathVariable(name = "id") Long id) {
        log.info(this.getClass().getName() + ": start findEmployeeById" +
                "\n\tParam\n\t\t" + id);
        return employeeService.findEmployeeById(id);
    }

    /**
     * Sửa thông tin nhân viên theo id
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Sửa thông tin nhân viên theo id")
    EmployeeResponse editEmployeeById(@PathVariable(name = "id") Long id, @RequestBody EmployeeRequest request) {
        log.info(this.getClass().getName() + ": start editEmployeeById" +
                "\n\tParam\n\t\t" + id  + "\n\t\t" + request);
        return employeeService.editEmployeeById(id, request);
    }

    /**
     * Xóa nhân viên theo id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Xóa nhân viên theo id")
    String deleteEmployeeById(@PathVariable(name = "id") Long id) {
        log.info(this.getClass().getName() + ": start deleteEmployeeById" +
                "\n\tParam\n\t\t" + id);
        employeeService.deleteEmployeeById(id);
        return "Thành công";
    }
}
