package com.example.smartcampus.smartcampus.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$")
    private String otpCode;
    @NotBlank
    @Size(min =6)
    private String newPassword;
}
