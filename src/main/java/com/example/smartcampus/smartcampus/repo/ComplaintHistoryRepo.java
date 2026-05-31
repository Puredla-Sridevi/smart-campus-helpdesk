package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.ComplaintHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintHistoryRepo extends JpaRepository<ComplaintHistory,Long> {
    Page<ComplaintHistory> findAllByComplaint(Complaint complaint, Pageable pageable);
}
