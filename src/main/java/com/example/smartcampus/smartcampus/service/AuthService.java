package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.dtos.*;

public interface AuthService {
 String registration(RegisterRequestDto registerRequestDto);
  AuthResponseDto login(LoginRequestDto loginRequestDto);
String  otpVerification(OtpVerificationRequestDto verificationRequestDto);
  String forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto);
  String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
}
