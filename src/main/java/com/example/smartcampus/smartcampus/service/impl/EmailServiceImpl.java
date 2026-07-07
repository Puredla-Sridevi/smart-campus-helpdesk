package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.entity.Complaint;
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

    @Override
    public void sendComplaintAssignNotify(Complaint complaint,String staffEmail) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();

        mailMessage.setTo(staffEmail);
        mailMessage.setSubject("Complaint Assigned");
        mailMessage.setText(
                "Hello,\n\n" +
                        "A new complaint has been assigned to you.\n\n" +
                        "Complaint Title: " + complaint.getTitle() + "\n" +
                        "Description: " + complaint.getDescription() + "\n" +
                        "Priority: " + complaint.getComplaintPriority() + "\n" +
                        "Status: " + complaint.getComplaintStatus() + "\n\n" +
                        "Please log in to the Smart Campus Portal to view and manage the complaint.\n\n" +
                        "Regards,\n" +
                        "Smart Campus Helpdesk"
        );
        mailSender.send(mailMessage);
    }
}
