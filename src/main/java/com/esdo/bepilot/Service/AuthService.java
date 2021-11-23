package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.AuthRequest;
import com.esdo.bepilot.Model.Request.CheckOTPRequest;
import com.esdo.bepilot.Model.Request.ForgotPasswordRequest;
import com.esdo.bepilot.Model.Request.ResetPasswordRequest;
import com.esdo.bepilot.Model.Response.AuthResponse;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ForgotPasswordResponse;

public interface AuthService {
    /**
     * Đăng nhập
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    AuthResponse login(AuthRequest authenticationRequest) throws Exception;

    /**
     * Gửi mã OTP về email
     * @param request
     * @return
     */
    ForgotPasswordResponse sendOTP (ForgotPasswordRequest request);

    /**
     * Kiểm tra mã OTP theo email
     * @param request
     * @return
     */
    Boolean checkOTP (CheckOTPRequest request);

    /**
     * Đổi mật khẩu
     * @param id
     * @param request
     * @return
     */
    EmployeeResponse reset(Long id, ResetPasswordRequest request);
}
