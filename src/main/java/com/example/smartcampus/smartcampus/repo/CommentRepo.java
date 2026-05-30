package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.dtos.CommentResponseDto;
import com.example.smartcampus.smartcampus.entity.Comment;
import com.example.smartcampus.smartcampus.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findAllByComplaint(Complaint complaint);
}
