package com.example.jobtracker.controller;

import com.example.jobtracker.dto.JobApplicationRequest;
import com.example.jobtracker.dto.JobApplicationResponse;
import com.example.jobtracker.dto.JobApplicationUpdateRequest;
import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.service.JobApplicationService;
import com.example.jobtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/job-applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    private final UserService userService;

    @PostMapping
    public JobApplicationResponse createJobApplication(
            @Valid @RequestBody JobApplicationRequest request
    ) {
        User user = userService.findByEmail("test@test.com")
                .orElseGet(() ->
                        userService.save(
                                User.builder()
                                        .email("test@test.com")
                                        .password("password")
                                        .build()
                        )
                );

        JobApplication application = JobApplication.builder()
                .companyName(request.getCompanyName())
                .position(request.getPosition())
                .status(request.getStatus())
                .appliedDate(LocalDate.now())
                .user(user)
                .build();

        JobApplication saved = jobApplicationService.create(application, user);

        return JobApplicationResponse.builder()
                .id(saved.getId())
                .companyName(saved.getCompanyName())
                .position(saved.getPosition())
                .status(saved.getStatus())
                .appliedDate(saved.getAppliedDate())
                .build();
    }


    @GetMapping
    public List<JobApplicationResponse> getMyApplications(){
        return jobApplicationService.findByUser(1L)
                .stream()
                .map(app -> JobApplicationResponse.builder()
                        .id(app.getId())
                        .companyName(app.getCompanyName())
                        .position(app.getPosition())
                        .status(app.getStatus())
                        .appliedDate(app.getAppliedDate())
                        .build()
                )
                .toList();
    }
    @PutMapping("/{id}")
    public JobApplicationResponse updateJobApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationUpdateRequest request
    ) {
        JobApplication updated = jobApplicationService.update(id, request);

        return JobApplicationResponse.builder()
                .id(updated.getId())
                .companyName(updated.getCompanyName())
                .position(updated.getPosition())
                .status(updated.getStatus())
                .appliedDate(updated.getAppliedDate())
                .build();
    }
}
