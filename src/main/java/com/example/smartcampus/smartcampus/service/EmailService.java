package com.example.smartcampus.smartcampus.service;

public interface EmailService {
    void sendOtpEmail(String email,String otpCode);
    void sendPasswordResetOtp(String email,String otpCode);
}
