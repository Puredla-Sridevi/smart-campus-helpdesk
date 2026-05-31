package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplaintService {
    ComplaintResponseDto createComplaint(ComplaintRequestDto complaintRequestDto);
    Page<ComplaintResponseDto> getMyComplaints(Pageable pageable);
    ComplaintResponseDto getComplaintById(Long id);
    ComplaintResponseDto updateComplaintStatus(Long id,UpdateComplaintStatusDto updateComplaintStatusDto);
    ComplaintResponseDto updateComplaintPriority(Long id, UpdateComplaintPriorityDto updateComplaintPriorityDto);
    Page<ComplaintResponseDto> getAllComplaints(Pageable pageable);
    Page<ComplaintResponseDto> getAllComplaintsByStatus(ComplaintStatus complaintStatus,Pageable pageable);
    Page<ComplaintResponseDto> getAllComplaintsByPriority(ComplaintPriority complaintPriority,Pageable pageable);
    DashBoardStatsResponseDto getDashboardStats();
    ComplaintResponseDto assignComplaint(Long complaintId,AssignComplaintDto assignComplaintDto);
    Page<ComplaintResponseDto> getAllComplaintsByAssignedTo(Pageable pageable);
    Page<ComplaintResponseDto> searchComplaints(String keyword,Pageable pageable);
  Page<ComplaintHistoryResponseDto> getComplaintHistory(Long complaintId,Pageable pageable);
  StaffDashBoardDto getStaffDashboard();
    ComplaintResponseDto getMyAssignedComplaint(Long id);
}
