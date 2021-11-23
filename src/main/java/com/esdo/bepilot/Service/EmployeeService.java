package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.EmployeeRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ListEmployeeResponse;

public interface EmployeeService {
    /**
     * Tạo mới nhân viên
     * @param employeeRequest
     * @return
     */
    EmployeeResponse createNewEmployee(EmployeeRequest employeeRequest);

    /**
     * Lấy thông tin danh sách nhân viên
     * @param page
     * @param size
     * @param key
     * @return
     */
    ListEmployeeResponse findAllEmployee(Integer page, Integer size, String key);

    /**
     * Lấy thông tin nhân viên theo id
     * @param id
     * @return
     */
    EmployeeResponse findEmployeeById(Long id);

    /**
     * Sửa thông tin nhân viên theo id
     * @param id
     * @param request
     * @return
     */
    EmployeeResponse editEmployeeById(Long id, EmployeeRequest request);

    /**
     * Xóa thông tin nhân viên theo id
     * @param id
     */
    void deleteEmployeeById(Long id);
}
