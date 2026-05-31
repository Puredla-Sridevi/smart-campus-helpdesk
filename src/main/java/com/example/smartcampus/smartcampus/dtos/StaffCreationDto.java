package com.example.smartcampus.smartcampus.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffCreationDto {
    @NotBlank
    @Size(min =3, max = 50)
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6,max = 100)
    private String password;
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
}
