package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
private final JavaMailSender mailSender;
    @Override
    public void sendOtpEmail(String email, String otpCode) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Smart Campus Account Verification");
        mailMessage.setText("Your Smart Campus verification OTP is: " +otpCode+
                "\n This OTP expires in 5 minutes.");
        mailSender.send(mailMessage);
    }

    @Override
    public void sendPasswordResetOtp(String email, String otpCode) {
SimpleMailMessage mailMessage =new SimpleMailMessage();
mailMessage.setTo(email);
mailMessage.setSubject("Smart Campus Password Reset");
mailMessage.setText("reset password otp: "+ otpCode);
mailSender.send(mailMessage);
    }
}
