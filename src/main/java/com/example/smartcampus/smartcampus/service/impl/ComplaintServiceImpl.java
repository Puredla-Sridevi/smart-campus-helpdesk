package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.entity.*;
import com.example.smartcampus.smartcampus.repo.ComplaintHistoryRepo;
import com.example.smartcampus.smartcampus.repo.ComplaintRepo;
import com.example.smartcampus.smartcampus.repo.UserRepo;
import com.example.smartcampus.smartcampus.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepo complaintRepo;
    private final UserRepo userRepo;
    private final ComplaintHistoryRepo complaintHistoryRepo;

    @Override
    public ComplaintResponseDto createComplaint(ComplaintRequestDto complaintRequestDto) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User name Not Found"));
        Complaint complaint=Complaint.builder()
                .description(complaintRequestDto.getDescription())
                .student(user)
                .title(complaintRequestDto.getTitle())
                .build();
        Complaint savedComplaint=complaintRepo.save(complaint);
        saveHistory(savedComplaint,"Complaint Created");
        return ComplaintResponseDto.builder()
                .id(savedComplaint.getId())
                .complaintStatus(savedComplaint.getComplaintStatus())
                .complaintPriority(savedComplaint.getComplaintPriority())
                .createdAt(savedComplaint.getCreatedAt())
                .description(savedComplaint.getDescription())
                .title(savedComplaint.getTitle())
                .studentName(savedComplaint.getStudent().getFullName())
                .assignedStaffName(savedComplaint.getAssignedTo() !=null ? savedComplaint.getAssignedTo().getFullName() : null)
                .build();
    }

    @Override
    public Page<ComplaintResponseDto> getMyComplaints(Pageable pageable) {
       Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       User student=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
       Page<Complaint> complaints=complaintRepo.findAllByStudent(student,pageable);
      return complaints.map(complaint->ComplaintResponseDto.builder()
               .complaintStatus(complaint.getComplaintStatus())
               .complaintPriority(complaint.getComplaintPriority())
               .description(complaint.getDescription())
               .title(complaint.getTitle())
               .createdAt(complaint.getCreatedAt())
               .studentName(complaint.getStudent().getFullName())
               .id(complaint.getId())
              .assignedStaffName(complaint.getAssignedTo() !=null ? complaint.getAssignedTo().getFullName() : null)
              .build());

    }

    @Override
    public ComplaintResponseDto getComplaintById(Long id) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("no Complaint Exists with the id: "+id));
        User student=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        if(!(student.getId().equals(complaint.getStudent().getId())||student.getRole()==Role.ROLE_ADMIN)){
            throw new RuntimeException("UnAuthorized access");
        }
        return ComplaintResponseDto.builder()
                .complaintPriority(complaint.getComplaintPriority())
                .complaintStatus(complaint.getComplaintStatus())
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .createdAt(complaint.getCreatedAt())
                .studentName(complaint.getStudent().getFullName())
                .assignedStaffName(complaint.getAssignedTo() !=null ? complaint.getAssignedTo().getFullName() : null)
                .build();
    }

    @Override
    public ComplaintResponseDto updateComplaintStatus(Long id,UpdateComplaintStatusDto updateComplaintStatusDto) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User currentUser=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("No Complaint with given id: "+id));
       if(!currentUser.getId().equals(complaint.getAssignedTo().getId())){
            throw new RuntimeException("UnAuthorized Access");
        }

        ComplaintStatus oldComplaintStatus=complaint.getComplaintStatus();
        complaint.setComplaintStatus(updateComplaintStatusDto.getComplaintStatus());
        Complaint savedComplaint =complaintRepo.save(complaint);
        saveHistory(savedComplaint,"Status Updated from "+oldComplaintStatus+" to "+updateComplaintStatusDto.getComplaintStatus());
        return ComplaintResponseDto.builder()
                .id(savedComplaint.getId())
                .title(savedComplaint.getTitle())
                .description(savedComplaint.getDescription())
                .complaintPriority(savedComplaint.getComplaintPriority())
                .complaintStatus(savedComplaint.getComplaintStatus())
                .createdAt(savedComplaint.getCreatedAt())
                .studentName(savedComplaint.getStudent().getFullName())
                .assignedStaffName(savedComplaint.getAssignedTo() !=null ? savedComplaint.getAssignedTo().getFullName() : null)
                .build();
    }

    @Override
    public ComplaintResponseDto updateComplaintPriority(Long id, UpdateComplaintPriorityDto updateComplaintPriorityDto) {
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("No Complaint exists with id : "+id));
        ComplaintPriority oldComplaintPriority=complaint.getComplaintPriority();
complaint.setComplaintPriority(updateComplaintPriorityDto.getComplaintPriority());
        Complaint savedComplaint =complaintRepo.save(complaint);
        saveHistory(savedComplaint,"priority updated from "+oldComplaintPriority+" to "+savedComplaint.getComplaintPriority());
        return ComplaintResponseDto.builder()
                .id(savedComplaint.getId())
                .title(savedComplaint.getTitle())
                .description(savedComplaint.getDescription())
                .complaintPriority(savedComplaint.getComplaintPriority())
                .complaintStatus(savedComplaint.getComplaintStatus())
                .createdAt(savedComplaint.getCreatedAt())
                .studentName(savedComplaint.getStudent().getFullName())
                .assignedStaffName(savedComplaint.getAssignedTo() !=null ? savedComplaint.getAssignedTo().getFullName() : null)
                .build();
    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaints(Pageable pageable) {
        Page<Complaint> complaints=complaintRepo.findAll(pageable);
           return complaints.map(complaint ->ComplaintResponseDto.builder()
                    .complaintStatus(complaint.getComplaintStatus())
                    .complaintPriority(complaint.getComplaintPriority())
                    .description(complaint.getDescription())
                    .title(complaint.getTitle())
                    .createdAt(complaint.getCreatedAt())
                    .studentName(complaint.getStudent().getFullName())
                    .id(complaint.getId())
                   .assignedStaffName(complaint.getAssignedTo() !=null ? complaint.getAssignedTo().getFullName() : null)
                   .build());
    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaintsByStatus(ComplaintStatus complaintStatus,Pageable pageable) {
        Page<Complaint> complaints=complaintRepo.findAllByComplaintStatus(complaintStatus,pageable);
       return  complaints.map(complaint -> ComplaintResponseDto.builder()
                        .complaintStatus(complaint.getComplaintStatus())
                        .complaintPriority(complaint.getComplaintPriority())
                        .description(complaint.getDescription())
                        .title(complaint.getTitle())
                        .createdAt(complaint.getCreatedAt())
                        .studentName(complaint.getStudent().getFullName())
                        .id(complaint.getId())
               .assignedStaffName(complaint.getAssignedTo() !=null ? complaint.getAssignedTo().getFullName() : null)
               .build());

    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaintsByPriority(ComplaintPriority complaintPriority,Pageable pageable) {
        Page<Complaint> complaints=complaintRepo.findAllByComplaintPriority(complaintPriority,pageable);


           return complaints.map(complaint->ComplaintResponseDto.builder()
                    .complaintStatus(complaint.getComplaintStatus())
                    .complaintPriority(complaint.getComplaintPriority())
                    .description(complaint.getDescription())
                    .title(complaint.getTitle())
                    .createdAt(complaint.getCreatedAt())
                    .studentName(complaint.getStudent().getFullName())
                    .id(complaint.getId())
                   .assignedStaffName(complaint.getAssignedTo() !=null ?complaint.getAssignedTo().getFullName() : null)
                   .build());
    }

    @Override
    public DashBoardStatsResponseDto getDashboardStats() {
       return DashBoardStatsResponseDto.builder()
               .inProgressComplaints(complaintRepo.countByComplaintStatus(ComplaintStatus.IN_PROGRESS))
               .openComplaints(complaintRepo.countByComplaintStatus(ComplaintStatus.OPEN))
               .resolvedComplaints(complaintRepo.countByComplaintStatus(ComplaintStatus.RESOLVED))
               .highPriorityComplaints(complaintRepo.countByComplaintPriority(ComplaintPriority.HIGH))
               .totalComplaints(complaintRepo.count())
               .build();
    }

    @Override
    public ComplaintResponseDto assignComplaint(Long complaintId, AssignComplaintDto assignComplaintDto) {
        Complaint complaint=complaintRepo.findById(complaintId).orElseThrow(()->new RuntimeException("No Complaint Exists"));
        User staff=userRepo.findById(assignComplaintDto.getStaffId()).orElseThrow(()->new UsernameNotFoundException("No User Exists with id: "+assignComplaintDto.getStaffId()));
        if(!staff.getRole().equals(Role.ROLE_STAFF)){
            throw new RuntimeException("User Is Not Staff");
        }
        complaint.setAssignedTo(staff);
        Complaint savedComplaint=complaintRepo.save(complaint);
        saveHistory(savedComplaint,"complaint Assigned to "+savedComplaint.getAssignedTo().getFullName());
        return ComplaintResponseDto.builder()
                .assignedStaffName(savedComplaint.getAssignedTo().getFullName())
                .complaintStatus(savedComplaint.getComplaintStatus())
                .complaintPriority(savedComplaint.getComplaintPriority())
                .id(savedComplaint.getId())
                .title(savedComplaint.getTitle())
                .description(savedComplaint.getDescription())
                .createdAt(savedComplaint.getCreatedAt())
                .studentName(savedComplaint.getStudent().getFullName())
                .build();
    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaintsByAssignedTo(Pageable pageable) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User staff=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Exists"));
        Page<Complaint> complaints=complaintRepo.findAllByAssignedTo(staff,pageable);
        return complaints.map(complaint -> ComplaintResponseDto.builder()
                .assignedStaffName(complaint.getAssignedTo().getFullName())
                .complaintPriority(complaint.getComplaintPriority())
                .complaintStatus(complaint.getComplaintStatus())
                .id(complaint.getId())
                .createdAt(complaint.getCreatedAt())
                .title(complaint.getTitle())
                .studentName(complaint.getStudent().getFullName())
                .description(complaint.getDescription())
                .build());
    }

    @Override
    public Page<ComplaintResponseDto> searchComplaints(String keyword, Pageable pageable) {
        Page<Complaint> complaints=complaintRepo.findAllByTitleContainingIgnoreCase(keyword,pageable);
        return complaints.map(complaint -> ComplaintResponseDto.builder()
                .assignedStaffName(complaint.getAssignedTo() !=null ?complaint.getAssignedTo().getFullName() : null)               .complaintPriority(complaint.getComplaintPriority())
                .complaintStatus(complaint.getComplaintStatus())
                .id(complaint.getId())
                .createdAt(complaint.getCreatedAt())
                .title(complaint.getTitle())
                .studentName(complaint.getStudent().getFullName())
                .description(complaint.getDescription())
                .build());
    }

    @Override
    public Page<ComplaintHistoryResponseDto> getComplaintHistory(Long complaintId, Pageable pageable) {
        Complaint complaint=complaintRepo.findById(complaintId).orElseThrow(()-> new RuntimeException("no Complaint Exists With Id : "+complaintId));
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User staff=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Exists"));
        boolean isStudent=staff.getFullName().equalsIgnoreCase(complaint.getStudent().getFullName());
        boolean isStaff=complaint.getAssignedTo()!=null && staff.getFullName().equalsIgnoreCase(complaint.getAssignedTo().getFullName());

        if(!(isStudent || isStaff || staff.getRole()==Role.ROLE_ADMIN)){
            throw new RuntimeException("Access Denied");
        }
        Page<ComplaintHistory> complaintHistories=complaintHistoryRepo.findAllByComplaint(complaint,pageable);
      return  complaintHistories.map(
                complaintHistory ->
                        ComplaintHistoryResponseDto.builder()
                                .createdAt(complaintHistory.getCreatedAt())
                                .action(complaintHistory.getAction())
                                .performedBy(complaintHistory.getPerformedBy().getFullName())
                                .id(complaintHistory.getId())
                                .build()
                        );
    }

    @Override
    public StaffDashBoardDto getStaffDashboard() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User staff=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("No User Exists"));
        return StaffDashBoardDto.builder()
                .inProgressComplaints(complaintRepo.countByComplaintStatusAndAssignedTo(ComplaintStatus.IN_PROGRESS,staff))
                .openComplaints(complaintRepo.countByComplaintStatusAndAssignedTo(ComplaintStatus.OPEN,staff))
                .resolvedComplaints(complaintRepo.countByComplaintStatusAndAssignedTo(ComplaintStatus.RESOLVED,staff))
                .highPriorityComplaints(complaintRepo.countByComplaintPriorityAndAssignedTo(ComplaintPriority.HIGH,staff))
                .totalAssignedComplaints(complaintRepo.countByAssignedTo(staff))
                .build();
    }

    @Override
    public StudentDashBoardDto getStudentDashboard() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User student=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("No User Exists"));
        return StudentDashBoardDto.builder()
                .inProgressComplaints(complaintRepo.countByComplaintStatusAndStudent(ComplaintStatus.IN_PROGRESS,student))
                .openComplaints(complaintRepo.countByComplaintStatusAndStudent(ComplaintStatus.OPEN,student))
                .resolvedComplaints(complaintRepo.countByComplaintStatusAndStudent(ComplaintStatus.RESOLVED,student))
                .totalComplaints(complaintRepo.countByStudent(student))
                .build();
    }

    @Override
    public ComplaintResponseDto getMyAssignedComplaint(Long id) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User staff=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Exists"));
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("No Complaint Exists with id: "+id));
        if(!complaint.getAssignedTo().getId().equals(staff.getId())){
            throw new RuntimeException("UnAuthorized Access");
        }
        return ComplaintResponseDto.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .complaintPriority(complaint.getComplaintPriority())
                .complaintStatus(complaint.getComplaintStatus())
                .createdAt(complaint.getCreatedAt())
                .studentName(complaint.getStudent().getFullName())
                .assignedStaffName(complaint.getAssignedTo() !=null ? complaint.getAssignedTo().getFullName() : null)
                .build();
    }

    private void saveHistory(Complaint complaint,String action){
Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
User currentUser=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("No USer Exists"));
ComplaintHistory complaintHistory=ComplaintHistory.builder()
        .action(action)
        .complaint(complaint)
        .performedBy(currentUser)
        .build();
complaintHistoryRepo.save(complaintHistory);
 }
}
