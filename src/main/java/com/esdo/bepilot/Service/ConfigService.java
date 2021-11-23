package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.ConfigRequest;
import com.esdo.bepilot.Model.Response.ConfigResponse;

public interface ConfigService {
    /**
     * Lấy thông tin cấu hình
     * @return
     */
    ConfigResponse getConfig();

    /**
     * Sửa thông tin cấu hình
     * @param request
     * @return
     */
    ConfigResponse editConfig(ConfigRequest request);
}
