package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import com.esdo.bepilot.Model.Request.CustomerRequest;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import com.esdo.bepilot.Model.Response.ResponseEntity;
import com.esdo.bepilot.Model.Response.TransactionOfCustomerResponse;
import com.esdo.bepilot.Service.Implement.CustomerServiceImpl;
import com.esdo.bepilot.Service.Implement.TransactionServiceImpl;
import com.esdo.bepilot.Service.Validate.CustomerValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    TransactionServiceImpl transactionOfCustomerService;

    @Autowired
    CustomerValidate customerValidate;


    /**
     * Lấy danh sách khách hàng
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                                         required = false) int pageSize) {
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllCustomer of customerAPI ");
        List<CustomerResponse> responses = customerService.getAllCustomer(pageIndex, pageSize);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }

    /**
     * Thêm mới một khách hàng
     * @param request
     * @return
     */
    @PostMapping(value = "/customers/add")
    public String createCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Inside createCustomer of customerAPI ");
        customerValidate.validate(request);
        customerService.create(request);
        return "Created Complete";
    }

    /**
     * Lấy chi tiết một khách hàng theo id
     * @param id
     * @return
     */
    @GetMapping(value = "customers/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        log.info("Inside getCustomerById of customerAPI ");
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return customerResponse;
    }

    /**
     * lấy danh sách nạp tiền của một khách hàng
     * @param id
     * @return
     */
    @GetMapping(value = "customers/{id}/deposited")
    public ResponseEntity<List<TransactionOfCustomerResponse>> getListHistoryDeposited(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                                       @RequestParam(value = "pageSize", defaultValue = "10",
                                                                                               required = false) int pageSize,
                                                                                       @PathVariable Long id) {
        log.info("Inside getListHistoryDeposited of customerAPI ");
        ResponseEntity response = new ResponseEntity();
        log.info("Inside getAllCustomer of customerAPI ");
        List<TransactionOfCustomerResponse> responses = transactionOfCustomerService.getTransactionOfCustomerByCustomerId(pageIndex, pageSize, id);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());
        return response;

    }

    /**
     * Chỉnh sửa một khách hàng
     * @param phone
     * @param name
     * @param id
     * @return
     */
    @PutMapping(value = "/customers/{id}")
    public String updateCustomer(@RequestParam(name = "phone") String phone,
                                           @RequestParam(name = "name") String name,
                                           @PathVariable(name = "id") long id) {
        log.info("Inside updateCustomer of customerAPI ");
        if(phone == "") {
            return "Phone is null" ;
        }
        if(name == "") {
            return "Name is null" ;
        }
        customerService.updateCustomerById(id, name, phone);
        return "Update completed" ;
    }

    /**
     * xóa một khách hàng
     * @param id
     * @return
     */
    @DeleteMapping(value = "/customers/delete")
    public String deleteCustomer(@RequestParam Long id) {
        log.info("Inside deleteCustomer of customerAPI ");
        customerService.deleteCustomerById(id);
        return "Delete Completed" ;
    }

    /**
     * tìm kiếm khách hàng
     * @param pageIndex
     * @param pageSize
     * @param keyWord
     * @return
     */
    @GetMapping(value = "/customers/search")
    public ResponseEntity<List<CustomerResponse>> searchCustomer(@RequestParam(value = "pageIndex", defaultValue = "0",
            required = false) int pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",
                                                                         required = false) int pageSize,
                                                                 @RequestParam(name = "keyWord") String keyWord) {

        if (keyWord == null) {
            keyWord = "";
        }
        ResponseEntity response = new ResponseEntity();
        log.info("Inside searchCustomer of customerAPI ");
        List<CustomerResponse> responses = customerService.searchCustomer(pageIndex, pageSize, keyWord);
        response.setData(responses);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPage(responses.size() / pageSize);
        response.setTotalObject(responses.size());

        return response;
    }
}
