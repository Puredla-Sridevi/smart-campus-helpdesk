package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponseDto {
    private Long id;
    private String title;
    private String description;
    private ComplaintStatus complaintStatus;
    private ComplaintPriority complaintPriority;
    private LocalDateTime createdAt;
    private String studentName;
}
