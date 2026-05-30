package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.dtos.ComplaintRequestDto;
import com.example.smartcampus.smartcampus.dtos.ComplaintResponseDto;
import com.example.smartcampus.smartcampus.dtos.UpdateComplaintPriorityDto;
import com.example.smartcampus.smartcampus.dtos.UpdateComplaintStatusDto;
import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComplaintService {
    ComplaintResponseDto createComplaint(ComplaintRequestDto complaintRequestDto);
    Page<ComplaintResponseDto> getMyComplaints(Pageable pageable);
    ComplaintResponseDto getComplaintById(Long id);
    ComplaintResponseDto updateComplaintStatus(Long id,UpdateComplaintStatusDto updateComplaintStatusDto);
    ComplaintResponseDto updateComplaintPriority(Long id, UpdateComplaintPriorityDto updateComplaintPriorityDto);
    Page<ComplaintResponseDto> getAllComplaints(Pageable pageable);
    Page<ComplaintResponseDto> getAllComplaintsByStatus(ComplaintStatus complaintStatus,Pageable pageable);
    Page<ComplaintResponseDto> getAllComplaintsByPriority(ComplaintPriority complaintPriority,Pageable pageable);
}
