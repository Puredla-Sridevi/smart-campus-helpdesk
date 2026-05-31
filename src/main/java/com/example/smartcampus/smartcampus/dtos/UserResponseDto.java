package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private Boolean blocked;
}
