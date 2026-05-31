package com.example.smartcampus.smartcampus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "complaint_id",nullable = false)
    private Complaint complaint;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User performedBy;
    @PrePersist
    public void createdAt(){
        this.createdAt=LocalDateTime.now();
    }
}
