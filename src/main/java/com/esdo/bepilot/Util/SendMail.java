package com.esdo.bepilot.Util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail {

    /**
     * Gửi mã OTP về email
     * @param toEmail
     * @param subject
     * @param otp
     * @param javaMailSender
     */
    public static void send(String toEmail, String subject, Integer otp, JavaMailSender javaMailSender) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);

        msg.setSubject(subject);
        msg.setText("Mã OTP của bạn là " + otp);

        javaMailSender.send(msg);
    }
}
