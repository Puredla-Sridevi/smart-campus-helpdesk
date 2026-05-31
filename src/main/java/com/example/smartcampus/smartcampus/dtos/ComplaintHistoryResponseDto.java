package com.example.smartcampus.smartcampus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintHistoryResponseDto {
    private Long id;
    private String action;
    private LocalDateTime createdAt;
    private String performedBy;
}
