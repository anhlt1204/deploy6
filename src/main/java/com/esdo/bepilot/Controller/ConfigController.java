package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.ConfigRequest;
import com.esdo.bepilot.Model.Response.ConfigResponse;
import com.esdo.bepilot.Service.ConfigService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * Lấy thông tin cấu hình
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Lấy thông tin cấu hình")
    ConfigResponse getConfig() {
        log.info(this.getClass().getName() + ": start getConfig");
        return configService.getConfig();
    }

    /**
     * Sửa thông tin cấu hình
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Sửa thông tin cấu hình")
    ConfigResponse editConfig(@RequestBody ConfigRequest request) {
        log.info(this.getClass().getName() +
                ": start editConfig" + "\n\tParam\n\t\t" + request);
        return configService.editConfig(request);
    }
}
