package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateComplaintPriorityDto{
    private ComplaintPriority complaintPriority;
}
