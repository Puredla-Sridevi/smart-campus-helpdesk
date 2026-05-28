package com.example.smartcampus.smartcampus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="otp_verification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otpCode;
    private LocalDateTime expiryTime;
    private LocalDateTime createdAt;
    private boolean used;
    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;
    @PrePersist
    public void createdAt(){
        this.createdAt=LocalDateTime.now();
        this.expiryTime=createdAt.plusMinutes(5);
    }
    @PreUpdate
    public void updateAt(){

        this.expiryTime=LocalDateTime.now().plusMinutes(5);
    }
}
