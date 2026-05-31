package com.example.smartcampus.smartcampus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus complaintStatus;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintPriority complaintPriority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private User student;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User assignedTo;
    @PrePersist
    public void createdAt(){
         this.createdAt=LocalDateTime.now();
         this.updatedAt=LocalDateTime.now();
         this.complaintPriority=ComplaintPriority.LOW;
         this.complaintStatus=ComplaintStatus.OPEN;
    }
    @PreUpdate
    public void updatedAt(){
        this.updatedAt=LocalDateTime.now();
    }
}
