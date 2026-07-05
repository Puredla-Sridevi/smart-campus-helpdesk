package com.example.smartcampus.smartcampus.controller;

import com.example.smartcampus.smartcampus.dtos.*;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import com.example.smartcampus.smartcampus.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;
    @PostMapping
    public ResponseEntity<ComplaintResponseDto> createComplaint(@Valid @RequestBody ComplaintRequestDto complaintRequestDto){
       return ResponseEntity.ok(complaintService.createComplaint(complaintRequestDto));
    }
    @GetMapping("/my")
    public ResponseEntity<Page<ComplaintResponseDto>> getMyComplaints(@RequestParam(required = false,defaultValue ="0") int pageNo,
                                                                      @RequestParam(required = false,defaultValue = "5")int pageSize,
                                                                      @RequestParam(required = false,defaultValue ="id") String sortBy,
                                                                      @RequestParam(required = false,defaultValue = "ASC") String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(complaintService.getMyComplaints(PageRequest.of(pageNo,pageSize,sort)));
    }
 @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponseDto> getComplaintById(@PathVariable("id") Long id){
        return ResponseEntity.ok(complaintService.getComplaintById(id));
 }
 @PreAuthorize("hasRole('STAFF')")
  @PutMapping("/{id}/status")
  public ResponseEntity<ComplaintResponseDto> updateComplaintStatus(@PathVariable("id") Long id, @RequestBody UpdateComplaintStatusDto updateComplaintStatusDto){
        return ResponseEntity.ok(complaintService.updateComplaintStatus(id,updateComplaintStatusDto));
 }

 @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/priority")
    public ResponseEntity<ComplaintResponseDto> updateComplaintPriority(@PathVariable("id") Long id, @RequestBody UpdateComplaintPriorityDto updateComplaintPriorityDto){
        return ResponseEntity.ok(complaintService.updateComplaintPriority(id,updateComplaintPriorityDto));
 }

 @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<ComplaintResponseDto>> getAllComplaints(@RequestParam(required = false,defaultValue = "0")int pageNo,
                                                                       @RequestParam(required = false,defaultValue ="5") int pageSize,
                                                                       @RequestParam(required = false,defaultValue ="id") String sortBy,
                                                                       @RequestParam(required = false,defaultValue = "ASC") String sortDir){
     Sort sort=null;
     if(sortDir.equalsIgnoreCase("ASC")){
         sort=Sort.by(sortBy).ascending();
     }else{
         sort=Sort.by(sortBy).descending();
     }
        return ResponseEntity.ok(complaintService.getAllComplaints(PageRequest.of(pageNo,pageSize,sort)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<ComplaintResponseDto>> getAllComplaintsByStatus(@PathVariable("status")ComplaintStatus complaintStatus,
                                                                               @RequestParam(required = false,defaultValue = "0")int pageNo,
                                                                               @RequestParam(required = false,defaultValue ="5") int pageSize,
                                                                               @RequestParam(required = false,defaultValue ="id") String sortBy,
                                                                               @RequestParam(required = false,defaultValue = "ASC") String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(complaintService.getAllComplaintsByStatus(complaintStatus,PageRequest.of(pageNo,pageSize,sort)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("priority/{priority}")
    public ResponseEntity<Page<ComplaintResponseDto>> getAllComplaintsByPriority(@PathVariable("priority")ComplaintPriority complaintPriority,
                                                                                 @RequestParam(required = false,defaultValue = "0")int pageNo,
                                                                                 @RequestParam(required = false,defaultValue = "5")int pageSize,
                                                                                 @RequestParam(required = false,defaultValue ="id") String sortBy,
                                                                                 @RequestParam(required = false,defaultValue = "ASC") String sortDir
                                                                                 )
    {
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(complaintService.getAllComplaintsByPriority(complaintPriority,PageRequest.of(pageNo,pageSize,sort)));
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<DashBoardStatsResponseDto> getDashBoardStats(){
        return ResponseEntity.ok(complaintService.getDashboardStats());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/assign")
    public ResponseEntity<ComplaintResponseDto> assignComplaint(@PathVariable("id") Long complaintId,@Valid @RequestBody AssignComplaintDto assignComplaintDto){
        return ResponseEntity.ok(complaintService.assignComplaint(complaintId,assignComplaintDto));
    }
    @PreAuthorize("hasRole('STAFF')")
    @GetMapping("/assigned-to-me")
    public ResponseEntity<Page<ComplaintResponseDto>> getAllComplaintsByAssignedTo(@RequestParam(required = false,defaultValue ="0") int pageNo,
                                                                             @RequestParam(required = false,defaultValue = "5") int pageSize,
                                                                             @RequestParam(required = false,defaultValue = "id") String sortBy,
                                                                             @RequestParam(required = false,defaultValue = "ASC") String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(complaintService.getAllComplaintsByAssignedTo(PageRequest.of(pageNo,pageSize,sort)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<ComplaintResponseDto>> searchComplaints(
            @RequestParam String keyword,
            @RequestParam(required = false,defaultValue ="0") int pageNo,
            @RequestParam(required = false,defaultValue = "5") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "ASC") String sortDir
    ){
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
return ResponseEntity.ok(complaintService.searchComplaints(keyword,PageRequest.of(pageNo,pageSize,sort)));
    }

    @GetMapping("/{id}/history")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT','STAFF')")
    public ResponseEntity<Page<ComplaintHistoryResponseDto>> getComplaintHistory(@PathVariable("id") Long complaintId,
                                                                                 @RequestParam(required = false,defaultValue ="0") int pageNo,
                                                                                 @RequestParam(required = false,defaultValue = "5") int pageSize,
                                                                                 @RequestParam(required = false,defaultValue = "id") String sortBy,
                                                                                 @RequestParam(required = false,defaultValue = "ASC") String sortDir)
    {
        Sort sort=sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
         return ResponseEntity.ok(complaintService.getComplaintHistory(complaintId,PageRequest.of(pageNo,pageSize,sort)));
    }

    @GetMapping("/staff/dashboard")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<StaffDashBoardDto> getStaffDashboard(){
        return  ResponseEntity.ok(complaintService.getStaffDashboard());
    }

    @GetMapping("/student/dashboard")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentDashBoardDto> getStudentDashboard(){
        return  ResponseEntity.ok(complaintService.getStudentDashboard());
    }
    @PreAuthorize("hasRole('STAFF')")
    @GetMapping("/assigned-to-me/{id}")
    public ResponseEntity<ComplaintResponseDto> getComplaintByAssignedTo(@PathVariable("id") Long complaintId){
           return  ResponseEntity.ok(complaintService.getMyAssignedComplaint(complaintId));
    }

}
