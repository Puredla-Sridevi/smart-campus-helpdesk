package com.example.smartcampus.smartcampus.controllr;

import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto registerRequestDto){
       return  ResponseEntity.ok(authService.registration(registerRequestDto));
    }
 @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
 }
 @PostMapping("verifyOtp")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerificationRequestDto otpVerificationRequestDto){
        return ResponseEntity.ok(authService.otpVerification(otpVerificationRequestDto));
    }
    @PostMapping("forgotPassword")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto){
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordRequestDto));
    }
    @PostMapping("resetPassword")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequestDto resetPasswordRequestDto){
        return ResponseEntity.ok(authService.resetPassword(resetPasswordRequestDto));
    }
}
