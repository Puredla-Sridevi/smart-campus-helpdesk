package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import com.example.smartcampus.smartcampus.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Long> {
    Page<Complaint> findAllByStudent(User student, Pageable pageable);
    Page<Complaint> findAllByComplaintStatus(ComplaintStatus complaintStatus,Pageable pageable);
    Page<Complaint> findAllByComplaintPriority(ComplaintPriority complaintPriority,Pageable pageable);
}
