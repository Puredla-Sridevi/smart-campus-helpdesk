package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateComplaintStatusDto {
    ComplaintStatus complaintStatus;
}
