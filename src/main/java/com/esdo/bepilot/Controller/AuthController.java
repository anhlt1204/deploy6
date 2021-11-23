package com.esdo.bepilot.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.esdo.bepilot.Model.Request.*;
import com.esdo.bepilot.Repository.AccountRepository;
import com.esdo.bepilot.Service.AuthService;
import com.esdo.bepilot.Service.Validate.CheckValid;
import com.esdo.bepilot.Service.jwt.CustomUserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountRepository accountRepository;

    /**
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Đăng nhập")
    public ResponseEntity<?> login(@RequestBody AuthRequest authenticationRequest) throws Exception {
        log.info(this.getClass().getName() +
                ": start login" + "\n\tParam\n\t\t" + authenticationRequest);
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

    /**
     * Quên mật khẩu
     * @param request
     * @return
     */
    @PostMapping(value = "/forgot")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Quên mật khẩu")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        log.info(this.getClass().getName() +
                ": start forgotPassword" + "\n\tParam\n\t\t" + request);
        return ResponseEntity.ok(authService.sendOTP(request));
    }

    /**
     * Đổi mật khẩu
     * @param id
     * @param request
     * @return
     */
    @PatchMapping(value = "/reset/{id}")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Đổi mật khẩu")
    public ResponseEntity<?> reset(@PathVariable(name = "id") Long id,
                                   @RequestBody ResetPasswordRequest request) {
        log.info(this.getClass().getName() +
                ": start resetPassword" + "\n\tParam\n\t\t" + id + "\n\t\t" + request);
        return ResponseEntity.ok(authService.reset(id, request));
    }

    /**
     * Đăng ký tài khoản
     * @param account
     * @return
     */
    @PostMapping(value = "/register")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Đăng ký tài khoản")
    public ResponseEntity<?> saveAccount(@RequestBody AccountInputDTO account) {
        log.info(this.getClass().getName() +
                ": start saveAccount" + "\n\tParam\n\t\t" + account);
        CheckValid.checkAccountInputDTO(account, accountRepository);
        return ResponseEntity.ok(userDetailsService.save(account));
    }

    /**
     * Kiểm tra mã OTP
     * @param request
     * @return
     */
    @PostMapping(value = "/check")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Kiểm tra mã OTP")
    public ResponseEntity<?> checkOTP(@RequestBody CheckOTPRequest request) {
        log.info(this.getClass().getName() +
                ": start checkOTP" + "\n\tParam\n\t\t" + request);
        return ResponseEntity.ok(authService.checkOTP(request));
    }

    /**
     * Tải ảnh lên cloudianry
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/image")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("Tải ảnh lên cloudianry")
    public ResponseEntity<?> upload(@RequestParam("files") MultipartFile[] files) throws IOException {
        log.info(this.getClass().getName() + ": start upload" + "\n\tParam\n\t\t" + files);
        for (int i = 0; i < files.length; i++) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(files[i].getBytes(), ObjectUtils.emptyMap());
            System.out.println(cloudinaryMap.get("secure_url").toString());
            System.out.println(cloudinaryMap.get("public_id").toString());
        }

        return ResponseEntity.ok("Ok");
    }
}

