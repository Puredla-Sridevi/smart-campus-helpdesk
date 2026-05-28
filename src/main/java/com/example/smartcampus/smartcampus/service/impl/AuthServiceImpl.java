package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.config.JwtService;
import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.entity.OtpVerification;
import com.example.smartcampus.smartcampus.entity.Role;
import com.example.smartcampus.smartcampus.entity.User;
import com.example.smartcampus.smartcampus.repo.OtpVerificationRepo;
import com.example.smartcampus.smartcampus.repo.UserRepo;
import com.example.smartcampus.smartcampus.service.AuthService;
import com.example.smartcampus.smartcampus.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final OtpVerificationRepo otpVerificationRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final  AuthenticationManager authenticationManager;
    @Override
    public String registration(RegisterRequestDto registerRequestDto)  {
        String email=registerRequestDto.getEmail();
         if(userRepo.existsByEmail(email)){
             throw new RuntimeException("User Already Exists");
         }
         User user=User.builder()
                 .email(email)
                 .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                 .role(Role.ROLE_STUDENT)
                 .fullName(registerRequestDto.getFullName())
                 .phoneNumber(registerRequestDto.getPhoneNumber())
                 .enabled(false)
                 .blocked(false)
                 .build();
         User savedUser=userRepo.save(user);
         String otpCode =generateOtp();
        OtpVerification verification=OtpVerification.builder()
                .user(savedUser)
                .used(false)
                .otpCode(otpCode)
                .build();
        otpVerificationRepo.save(verification);
        emailService.sendOtpEmail(savedUser.getEmail(),otpCode);
        return "registration Successful.Otp Sent to email";
    }
private String generateOtp(){
    SecureRandom random=new SecureRandom();
    int otp=random.nextInt(900000)+100000;
    return String.valueOf(otp);
}
    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginRequestDto.getEmail(),
        loginRequestDto.getPassword()
));
        User user=userRepo.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        if(!user.isEnabled()){
            throw new RuntimeException("Account not verified");
        }
        if(user.isBlocked()){
            throw new RuntimeException("User is Blocked");
        }

       String token= jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .message("Login Successfully")
                .build();
    }

    @Override
    public String otpVerification(OtpVerificationRequestDto otpVerificationRequestDto) {
        User user=userRepo.findByEmail(otpVerificationRequestDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("USer Name Not Found"));
        OtpVerification otpVerification=otpVerificationRepo.findByUser(user).orElseThrow(()->new UsernameNotFoundException("No User Found"));
        if(otpVerification.isUsed()){
            throw  new RuntimeException("Otp cant be reused");
        }
        if( otpVerification.getExpiryTime().isBefore(LocalDateTime.now())){
            throw  new RuntimeException("Otp Expired");
        }
        if(otpVerification.getOtpCode().equals(otpVerificationRequestDto.getOtpCode())){
            user.setEnabled(true);
            otpVerification.setUsed(true);
            userRepo.save(user);
            otpVerificationRepo.save(otpVerification);
        }else{
            throw new RuntimeException("Otp Mismatched");
        }
        return "Otp Verification Done Successfully";
    }

    @Override
    public String forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) {
        String resetOtp=generateOtp();
        User user=userRepo.findByEmail(forgotPasswordRequestDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("User Name Not Found"));
        OtpVerification otpVerification=otpVerificationRepo.findByUser(user).orElseThrow(()->new RuntimeException("No User Found"));
        otpVerification.setOtpCode(resetOtp);
        otpVerification.setUsed(false);
        otpVerificationRepo.save(otpVerification);
        emailService.sendPasswordResetOtp(forgotPasswordRequestDto.getEmail(),resetOtp);
        return "Otp Sent Successfully";
    }

    @Override
    public String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        User user=userRepo.findByEmail(resetPasswordRequestDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("User name Not Found"));
        OtpVerification otpVerification=otpVerificationRepo.findByUser(user).orElseThrow(()->new RuntimeException("User Not Found"));
        if(otpVerification.isUsed()){
            throw new RuntimeException("Otp is Used");
        }
        if(otpVerification.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Otp is Expired");
        }
        if(!otpVerification.getOtpCode().equals(resetPasswordRequestDto.getOtpCode())){
            throw  new RuntimeException("Otp mismatched");
        }
        user.setPassword(passwordEncoder.encode(resetPasswordRequestDto.getNewPassword()));
        userRepo.save(user);
        otpVerification.setUsed(true);
        otpVerificationRepo.save(otpVerification);
        return "Password Changed Successfully";
    }
}
