package com.example.jobtracker.dto;

import com.example.jobtracker.entity.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class JobApplicationRequest {

    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must be at most 255 characters")
    private String companyName;

    @NotBlank(message = "Position is required")
    @Size(max = 255, message = "Position must be at most 255 characters")
    private String position;

    @NotNull(message = "Status is required")
    private ApplicationStatus status;
}
