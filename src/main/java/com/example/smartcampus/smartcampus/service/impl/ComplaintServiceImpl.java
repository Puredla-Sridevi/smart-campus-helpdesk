package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.dtos.ComplaintRequestDto;
import com.example.smartcampus.smartcampus.dtos.ComplaintResponseDto;
import com.example.smartcampus.smartcampus.dtos.UpdateComplaintPriorityDto;
import com.example.smartcampus.smartcampus.dtos.UpdateComplaintStatusDto;
import com.example.smartcampus.smartcampus.entity.*;
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

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepo complaintRepo;
    private final UserRepo userRepo;


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
        return ComplaintResponseDto.builder()
                .id(savedComplaint.getId())
                .complaintStatus(savedComplaint.getComplaintStatus())
                .complaintPriority(savedComplaint.getComplaintPriority())
                .createdAt(savedComplaint.getCreatedAt())
                .description(savedComplaint.getDescription())
                .title(savedComplaint.getTitle())
                .studentName(savedComplaint.getStudent().getFullName())
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
               .build());

    }

    @Override
    public ComplaintResponseDto getComplaintById(Long id) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("no Complaint Exists with the id: "+id));
        User student=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        if(!student.getId().equals(complaint.getStudent().getId())){
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
                .build();
    }

    @Override
    public ComplaintResponseDto updateComplaintStatus(Long id,UpdateComplaintStatusDto updateComplaintStatusDto) {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        User currentUser=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("No Complaint with given id: "+id));
//        if(!currentUser.getRole().equals(Role.ROLE_ADMIN)){
//            throw new RuntimeException("UnAuthorized Access");
//        }
        complaint.setComplaintStatus(updateComplaintStatusDto.getComplaintStatus());
        Complaint savedCompliant=complaintRepo.save(complaint);
        return ComplaintResponseDto.builder()
                .id(savedCompliant.getId())
                .title(savedCompliant.getTitle())
                .description(savedCompliant.getDescription())
                .complaintPriority(savedCompliant.getComplaintPriority())
                .complaintStatus(savedCompliant.getComplaintStatus())
                .createdAt(savedCompliant.getCreatedAt())
                .studentName(savedCompliant.getStudent().getFullName())
                .build();
    }

    @Override
    public ComplaintResponseDto updateComplaintPriority(Long id, UpdateComplaintPriorityDto updateComplaintPriorityDto) {
        Complaint complaint=complaintRepo.findById(id).orElseThrow(()->new RuntimeException("No Complaint exists with id : "+id));
complaint.setComplaintPriority(updateComplaintPriorityDto.getComplaintPriority());
        Complaint savedCompliant=complaintRepo.save(complaint);
        return ComplaintResponseDto.builder()
                .id(savedCompliant.getId())
                .title(savedCompliant.getTitle())
                .description(savedCompliant.getDescription())
                .complaintPriority(savedCompliant.getComplaintPriority())
                .complaintStatus(savedCompliant.getComplaintStatus())
                .createdAt(savedCompliant.getCreatedAt())
                .studentName(savedCompliant.getStudent().getFullName())
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
                    .build());
    }

}
