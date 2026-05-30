package com.example.smartcampus.smartcampus.controller;

import com.example.smartcampus.smartcampus.dtos.CommentRequestDto;
import com.example.smartcampus.smartcampus.dtos.CommentResponseDto;
import com.example.smartcampus.smartcampus.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    @PostMapping()
    public ResponseEntity<CommentResponseDto> createComment( @Valid @RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentService.createComment(commentRequestDto));
    }
    @GetMapping("/complaint/{id}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByComplaint(@PathVariable("id") Long complaintId){
        return ResponseEntity.ok(commentService.getAllCommentsByComplaint(complaintId));
    }
}
