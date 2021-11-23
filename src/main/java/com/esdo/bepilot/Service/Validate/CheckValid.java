package com.esdo.bepilot.Service.Validate;

import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Request.*;
import com.esdo.bepilot.Repository.AccountRepository;
import com.esdo.bepilot.Repository.EmployeeRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValid {

    // Kiểm tra email bắt đầu 1 hay nhiều bằng chữ cái hoặc số, có thể có '.' '_' hoặc không
    // tiếp theo là 1 hay nhiều chữ cái hoặc số
    // 1 ký tự @
    // ít nhất 2 chữ cái hoặc số
    // có thể miền cấp 1 hoặc cấp 2 (Ví dụ: .com hoặc .com.vn)
    // tên miền chỉ có từ 2 đến 4 ký tự
    private static final String regexEmail = "^([\\w]+[\\.\\_]*[\\w]+)@[\\w]{2,}(\\.[\\w]{2,6}){1,2}$";

    // Kiểm tra số bắt đầu bằng 0 hoặc +84 và đằng sau là 9 chữ số
    private static final String regexPhone = "^(0|\\+84)[0-9]{9}$";

    private static final String regexPassword = "^(?=.*\\d)(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

    static Pattern pattern;
    static Matcher matcher;

    /**
     * Kiểm tra email
     * @param email
     * @return
     */
    public static Boolean isEmail(String email) {
        pattern = Pattern.compile(regexEmail);
        matcher = pattern.matcher(email);
        return matcher.find();
    }

    /**
     * Kiểm tra số điện thoại
     * @param phone
     * @return
     */
    public static Boolean isPhone(String phone) {
        pattern = Pattern.compile(regexPhone);
        matcher = pattern.matcher(phone);
        return matcher.find();
    }

    /**
     * Kiểm tra mật khẩu
     * @param password
     * @return
     */
    public static Boolean isPassword(String password) {
        pattern = Pattern.compile(regexPassword);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    /**
     * Kiểm tra AuthRequest
     * @param request
     * @param accountRepository
     */
    public static void checkAuthRequest(AuthRequest request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        int flag = 0;
        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            throw new InvalidException("Tài khoản " + request.getEmail().trim() +" chưa tồn tại. Vui lòng kiểm tra lại.");
        }
    }

    /**
     * Kiểm tra EmployeeRequest
     * @param request
     * @param accountRepository
     * @param employeeRepository
     * @param isEdit
     */
    public static void checkEmployeeRequest(EmployeeRequest request, AccountRepository accountRepository,
                                            EmployeeRepository employeeRepository, boolean isEdit) {
        if (request.getAvatar().trim().length() == 0) {
            throw new InvalidException("Avatar is required");
        }

//        File avatar = new File(request.getAvatar().trim());
//
//        if (!avatar.exists()) {
//            throw new InvalidException("Invalid avatar");
//        }

        if (request.getName().trim().length() == 0) {
            throw new InvalidException("Name is required");
        }

        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (request.getPhone().trim().length() == 0) {
            throw new InvalidException("Phone is required");
        }

        if (request.getRole().trim().length() == 0) {
            throw new InvalidException("Role is required");
        }

        if (!isPhone(request.getPhone().trim())) {
            throw new InvalidException("Invalid Phone");
        }

        if (!isEmail(request.getEmail().trim())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> phones = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> phones.add(employee.getPhone()));

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        if (!isEdit) {
            for (String p : phones) {
                if (p.equals(request.getPhone().trim())) {
                    throw new InvalidException("Số điện thoại " + p + " đã tồn tại, vui lòng sử dụng số điện thoại khác");
                }
            }

            for (String e : emails) {
                if (e.equals(request.getEmail().trim())) {
                    throw new InvalidException("Email " + e + " đã tồn tại, vui lòng sử dụng email khác");
                }
            }
        }

        if (!request.getRole().trim().equals("ADMIN")) {
            if (!request.getRole().trim().equals("EMPLOYEE")) {
                throw new InvalidException("Role must be is ADMIN or EMPLOYEE");
            }
        }
    }

    /**
     * Kiểm tra AccountInputDTO
     * @param request
     * @param accountRepository
     */
    public static void checkAccountInputDTO(AccountInputDTO request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (request.getPassword().trim().length() == 0) {
            throw new InvalidException("Password is required");
        }

        if (request.getRole().trim().length() == 0) {
            throw new InvalidException("Role is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        if (!isPassword(request.getPassword())) {
            throw new InvalidException("Password must be eight characters including one letter, one number character");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                throw new InvalidException("Email " + e + " đã tồn tại, vui lòng sử dụng email khác");
            }
        }

        if (!request.getRole().trim().equals("ADMIN")) {
            if (!request.getRole().trim().equals("EMPLOYEE")) {
                throw new InvalidException("Role must be is ADMIN or EMPLOYEE");
            }
        }
    }

    /**
     * Kiểm tra ForgotPasswordRequest
     * @param request
     * @param accountRepository
     */
    public static void checkForgotPasswordRequest(ForgotPasswordRequest request, AccountRepository accountRepository) {
        if (request.getEmail().trim().length() == 0) {
            throw new InvalidException("Email is required");
        }

        if (!isEmail(request.getEmail())) {
            throw new InvalidException("Invalid Email");
        }

        List<String> emails = new ArrayList<>();
        accountRepository.findAll().forEach(account -> emails.add(account.getEmail()));

        int flag = 0;

        for (String e : emails) {
            if (e.equals(request.getEmail().trim())) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            throw new InvalidException("Email " + request.getEmail() + " chưa có tài khoản.");
        }
    }

    /**
     * Kiểm tra CheckOTPRequest
     * @param request
     */
    public static void checkCheckOTPRequest(CheckOTPRequest request) {
        if (request.getId() <= 0) {
            throw new InvalidException("Invalid account id");
        }

        if (request.getOtp().trim().length() == 0) {
            throw new InvalidException("OTP is required");
        }
    }

    /**
     * Kiểm tra ResetPasswordRequest
     * @param request
     */
    public static void checkResetPasswordRequest(ResetPasswordRequest request) {
        if (request.getNewPassword().trim().length() == 0) {
            throw new InvalidException("New Password is required");
        }

        if (request.getReNewPassword().trim().length() == 0) {
            throw new InvalidException("Renew Password is required");
        }

        if (request.getNewPassword().trim().length() == 0) {
            throw new InvalidException("New Password is required");
        }

        if (request.getReNewPassword().trim().length() == 0) {
            throw new InvalidException("Renew Password is required");
        }

        if (!request.getNewPassword().trim().equals(request.getReNewPassword().trim())) {
            throw new InvalidException("Renew Password is diffirent to New Password");
        }
    }

    /**
     * Kiểm tra ConfigRequest
     * @param request
     */
    public static void checkConfigRequest(ConfigRequest request) {
        if (request.getYtKeyMinTime() <= 0) {
            throw new InvalidException("Youtube - Chạy từ khóa - Min time không hợp lệ");
        }

        if (request.getYtKeyMaxTime() <= 0) {
            throw new InvalidException("Youtube - Chạy từ khóa - Max time không hợp lệ");
        }

        if (request.getYtKeyMaxTime() < request.getYtKeyMinTime()) {
            throw new InvalidException("Youtube - Chạy từ khóa - Max time không được nhỏ hơn Youtube - Chạy từ khóa - Min time");
        }

        if (request.getYtKeyCustomerPay() <= 0) {
            throw new InvalidException("Youtube - Chạy từ khóa - KH phải trả không hợp lệ");
        }

        if (request.getYtKeyUserReceived() <= 0) {
            throw new InvalidException("Youtube - Chạy từ khóa - ND nhận được không hợp lệ");
        }

        if (request.getYtKeyUserReceived() < request.getYtKeyCustomerPay()) {
            throw new InvalidException("Youtube - Chạy từ khóa - ND nhận được không được nhỏ hơn Youtube - Chạy từ khóa - KH phải trả");
        }

        if (request.getGgKeyMinTime() <= 0) {
            throw new InvalidException("Google - Chạy từ khóa - Min time không hợp lệ");
        }

        if (request.getGgKeyMaxTime() <= 0) {
            throw new InvalidException("Google - Chạy từ khóa - Max time không hợp lệ");
        }

        if (request.getGgKeyMaxTime() < request.getGgKeyMinTime()) {
            throw new InvalidException("Google - Chạy từ khóa - Max time không được nhỏ hơn Google - Chạy từ khóa - Min time");
        }

        if (request.getGgKeyCustomerPay() <= 0) {
            throw new InvalidException("Google - Chạy từ khóa - KH phải trả không hợp lệ");
        }

        if (request.getGgKeyUserReceived() <= 0) {
            throw new InvalidException("Google - Chạy từ khóa - ND nhận được không hợp lệ");
        }

        if (request.getGgKeyUserReceived() < request.getGgKeyCustomerPay()) {
            throw new InvalidException("Google - Chạy từ khóa - ND nhận được không được nhỏ hơn Google - Chạy từ khóa - KH phải trả");
        }

        if (request.getGgBackLinkMinTime() <= 0) {
            throw new InvalidException("Google - Chạy backlink - Min time không hợp lệ");
        }

        if (request.getGgBackLinkMaxTime() <= 0) {
            throw new InvalidException("Google - Chạy backlink - Max time không hợp lệ");
        }

        if (request.getGgBackLinkMaxTime() < request.getGgBackLinkMinTime()) {
            throw new InvalidException("Google - Chạy backlink - Max time không được nhỏ hơn Google - Chạy backlink - Min time");
        }

        if (request.getGgBackLinkCustomerPay() <= 0) {
            throw new InvalidException("Google - Chạy backlink - KH phải trả không hợp lệ");
        }

        if (request.getGgBackLinkUserReceived() <= 0) {
            throw new InvalidException("Google - Chạy backlink - ND nhận được không hợp lệ");
        }

        if (request.getGgBackLinkUserReceived() < request.getGgBackLinkCustomerPay()) {
            throw new InvalidException("Google - Chạy backlink - ND nhận được không được nhỏ hơn Google - Chạy backlink - KH phải trả");
        }

        if (request.getGgMissionMinTime() <= 0) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - Min time không hợp lệ");
        }

        if (request.getGgMissionMaxTime() <= 0) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - Max time không hợp lệ");
        }

        if (request.getGgMissionMaxTime() < request.getGgMissionMinTime()) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - Max time không được nhỏ hơn Google - Nhiệm vụ phức tạp - Min time");
        }

        if (request.getGgMissionCustomerPay() <= 0) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - KH phải trả không hợp lệ");
        }

        if (request.getGgMissionUserReceived() <= 0) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - ND nhận được không hợp lệ");
        }

        if (request.getGgMissionUserReceived() < request.getGgMissionCustomerPay()) {
            throw new InvalidException("Google - Nhiệm vụ phức tạp - ND nhận được không được nhỏ hơn Google - Nhiệm vụ phức tạp - KH phải trả");
        }

        if (request.getMaxMission() <= 0) {
            throw new InvalidException("Số nhiệm vụ tối đa/user/ngày không hợp lệ");
        }

        if (request.getMissionLifeCycle() <= 0) {
            throw new InvalidException("Vòng đời của nhiệm vụ không hợp lệ");
        }
    }
}
