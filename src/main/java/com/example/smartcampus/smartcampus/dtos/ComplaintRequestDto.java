package com.example.smartcampus.smartcampus.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequestDto {
    @NotBlank
    @Size(min=4,max =100)
    private String title;
    @NotBlank
    @Size(min=4,max = 1000)
    private String description;
}
