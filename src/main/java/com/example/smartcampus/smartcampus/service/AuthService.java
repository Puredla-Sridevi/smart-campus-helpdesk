package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.dtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthService {
 String registration(RegisterRequestDto registerRequestDto);
  AuthResponseDto login(LoginRequestDto loginRequestDto);
String  otpVerification(OtpVerificationRequestDto verificationRequestDto);
  String forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto);
  String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
    UserResponseDto createStaff(StaffCreationDto staffCreationDto);
    UserResponseDto blockUser(Long id,BlockUserDto blockUserDto);
    Page<UserResponseDto> getAllStaff(Pageable pageable);
    Page<UserResponseDto> getAllStudent(Pageable pageable);
}
