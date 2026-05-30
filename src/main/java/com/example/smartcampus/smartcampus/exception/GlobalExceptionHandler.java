package com.example.smartcampus.smartcampus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> usernameNotFoundExceptionHandler(UsernameNotFoundException e){
        ErrorResponseDto errorResponseDto=ErrorResponseDto.builder()
                .message(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(404)
                .build();
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> runtimeExceptionHandler(RuntimeException e){
        ErrorResponseDto errorResponseDto=ErrorResponseDto.builder()
                .message(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> accessDeniedExceptionHandler(AccessDeniedException e){
        ErrorResponseDto errorResponseDto=ErrorResponseDto.builder()
                .message(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(403)
                .build();
        return new ResponseEntity<>(errorResponseDto,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String,String> errors=new HashMap<>();
        for(FieldError error:e.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
