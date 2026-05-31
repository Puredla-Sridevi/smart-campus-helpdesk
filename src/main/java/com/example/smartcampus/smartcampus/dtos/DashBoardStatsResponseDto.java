package com.example.smartcampus.smartcampus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardStatsResponseDto {
    private Long totalComplaints;
    private Long openComplaints;
    private Long inProgressComplaints;
    private Long resolvedComplaints;
    private Long highPriorityComplaints;
}
