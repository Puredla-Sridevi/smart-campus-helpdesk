package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import com.example.smartcampus.smartcampus.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Long>, JpaSpecificationExecutor<Complaint> {
    Page<Complaint> findAllByStudent(User student, Pageable pageable);
    Page<Complaint> findAllByComplaintStatus(ComplaintStatus complaintStatus,Pageable pageable);
    Page<Complaint> findAllByComplaintPriority(ComplaintPriority complaintPriority,Pageable pageable);
    Long countByComplaintStatus(ComplaintStatus complaintStatus);
    Long countByComplaintPriority(ComplaintPriority complaintPriority);
    Page<Complaint> findAllByAssignedTo(User staff,Pageable pageable);
    Page<Complaint> findAllByTitleContainingIgnoreCase(String title,Pageable pageable);
    Long countByComplaintStatusAndAssignedTo(ComplaintStatus complaintStatus,User staff);
    Long countByComplaintPriorityAndAssignedTo(ComplaintPriority complaintPriority,User staff);
    Long countByAssignedTo(User staff);
    Long countByComplaintStatusAndStudent(ComplaintStatus complaintStatus,User student);
    Long countByComplaintPriorityAndStudent(ComplaintPriority complaintPriority,User student);
    Long countByStudent(User student);
}
