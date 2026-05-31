package com.example.smartcampus.smartcampus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDashBoardDto {
    private Long totalAssignedComplaints;
    private Long openComplaints;
    private Long inProgressComplaints;
    private Long resolvedComplaints;
    private Long highPriorityComplaints;
}
