package com.example.smartcampus.smartcampus.service;

import com.example.smartcampus.smartcampus.dtos.CommentRequestDto;
import com.example.smartcampus.smartcampus.dtos.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto commentRequestDto);
    List<CommentResponseDto> getAllCommentsByComplaint(Long id);
}
