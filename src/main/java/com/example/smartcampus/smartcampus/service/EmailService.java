package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.entity.Complaint;

public interface EmailService {
    void sendOtpEmail(String email,String otpCode);
    void sendPasswordResetOtp(String email,String otpCode);

    void sendComplaintAssignNotify(Complaint complaint, String staffEmail);
}
