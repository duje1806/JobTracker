package com.example.jobtracker.dto;

import com.example.jobtracker.entity.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class JobApplicationResponse {

    private Long id;
    private String companyName;
    private String position;
    private ApplicationStatus status;
    private LocalDate appliedDate;
}
