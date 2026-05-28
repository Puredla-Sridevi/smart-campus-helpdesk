package com.example.smartcampus.smartcampus.dtos;

import com.example.smartcampus.smartcampus.entity.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String email;
    @Size(min =6)
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp ="^[0-9]{10}$")
    private  String phoneNumber;
}
