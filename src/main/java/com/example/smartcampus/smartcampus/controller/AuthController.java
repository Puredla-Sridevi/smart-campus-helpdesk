package com.example.smartcampus.smartcampus.controller;

import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public  class AuthController {
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
    @PostMapping("admin/staff")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createStaff(@Valid @RequestBody StaffCreationDto staffCreationDto){
        return ResponseEntity.ok(authService.createStaff(staffCreationDto));
    }
    @PutMapping("{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> blockUser(@PathVariable("id") Long userId,@Valid @RequestBody BlockUserDto blockUserDto){
        return ResponseEntity.ok(authService.blockUser(userId,blockUserDto));
    }
    @GetMapping("admin/staff")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponseDto>> getAllStaff(@RequestParam(required = false,defaultValue ="0") int pageNo,
                                                             @RequestParam(required = false,defaultValue = "5") int pageSize,
                                                             @RequestParam(required = false,defaultValue = "id") String sortBy,
                                                             @RequestParam(required = false,defaultValue = "ASC") String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(authService.getAllStaff(PageRequest.of(pageNo,pageSize,sort)));
    }
    @GetMapping("admin/student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponseDto>> getAllStudent(@RequestParam(required = false,defaultValue ="0") int pageNo,
                                                             @RequestParam(required = false,defaultValue = "5") int pageSize,
                                                             @RequestParam(required = false,defaultValue = "id") String sortBy,
                                                             @RequestParam(required = false,defaultValue = "ASC") String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(authService.getAllStudent(PageRequest.of(pageNo,pageSize,sort)));
    }
}