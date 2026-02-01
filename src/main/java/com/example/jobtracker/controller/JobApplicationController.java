package com.example.jobtracker.controller;

import com.example.jobtracker.dto.JobApplicationResponse;
import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.service.JobApplicationService;
import com.example.jobtracker.service.UserService;
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
    public JobApplication createJobApplication(@RequestBody JobApplication application) {
      User user = userService.findByEmail("test@test.com")
              .orElseGet(() ->
                      userService.save(
                              User.builder()
                                      .email("test@test.com")
                                      .password("password")
                                      .build()
                      )
              );
        application.setAppliedDate(LocalDate.now());

      return jobApplicationService.create(application, user);
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
}
