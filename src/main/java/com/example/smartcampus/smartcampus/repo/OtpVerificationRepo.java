package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.entity.OtpVerification;
import com.example.smartcampus.smartcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepo extends JpaRepository<OtpVerification,Long> {
    Optional<OtpVerification> findByUser(User user);
    Optional<OtpVerification> findByOtpCode(String otpCode);
}
