package com.example.smartcampus.smartcampus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDashBoardDto {
    private Long openComplaints;
    private Long inProgressComplaints;
    private Long resolvedComplaints;
  private Long totalComplaints;
}
