package com.example.smartcampus.smartcampus.service.impl;

import com.example.smartcampus.smartcampus.dtos.CommentRequestDto;
import com.example.smartcampus.smartcampus.dtos.CommentResponseDto;
import com.example.smartcampus.smartcampus.entity.Comment;
import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.User;
import com.example.smartcampus.smartcampus.repo.CommentRepo;
import com.example.smartcampus.smartcampus.repo.ComplaintRepo;
import com.example.smartcampus.smartcampus.repo.UserRepo;
import com.example.smartcampus.smartcampus.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
private final ComplaintRepo complaintRepo;
private final UserRepo userRepo;
private final CommentRepo commentRepo;
    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        Complaint complaint=complaintRepo.findById(commentRequestDto.getComplaintId()).orElseThrow(()->new RuntimeException("No Complaint Found On Id : "+commentRequestDto.getComplaintId()));
        Comment comment=Comment.builder()
                .user(user)
                .complaint(complaint)
                .message(commentRequestDto.getMessage())
                .build();
        Comment savedComment=commentRepo.save(comment);
        return CommentResponseDto.builder()
                .id(savedComment.getId())
                .createdAt(savedComment.getCreatedAt())
                .message(savedComment.getMessage())
                .userName(savedComment.getUser().getFullName())
                .build();
    }

    @Override
    public List<CommentResponseDto> getAllCommentsByComplaint(Long id) {
        Complaint complaint = complaintRepo.findById(id).orElseThrow(() -> new RuntimeException("No Complaint Found with id :" + id));
        List<Comment> comments= commentRepo.findAllByComplaint(complaint);
        if(comments.isEmpty()){
            return new ArrayList<>();
        }
        List<CommentResponseDto> commentResponseDtos=new ArrayList<>();
        for(Comment comment:comments){
            commentResponseDtos.add(CommentResponseDto.builder()
                    .id(comment.getId())
                    .createdAt(comment.getCreatedAt())
                    .userName(comment.getUser().getFullName())
                    .message(comment.getMessage())
                    .build());
        }
        return commentResponseDtos;
    }
}
