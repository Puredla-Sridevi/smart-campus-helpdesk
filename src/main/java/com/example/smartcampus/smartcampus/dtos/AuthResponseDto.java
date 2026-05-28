package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private String message;
    private Role role;
    private String email;
}
