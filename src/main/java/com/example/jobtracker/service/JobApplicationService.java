package com.example.jobtracker.service;

import com.example.jobtracker.dto.JobApplicationRequest;
import com.example.jobtracker.dto.JobApplicationUpdateRequest;
import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.exception.ForbiddenException;
import com.example.jobtracker.exception.ResourceNotFoundException;
import com.example.jobtracker.repository.JobApplicationRepository;
import com.example.jobtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplication create(JobApplicationRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        JobApplication application = JobApplication.builder()
                .companyName(request.getCompanyName())
                .position(request.getPosition())
                .status(request.getStatus())
                .appliedDate(LocalDate.now())
                .user(user)
                .build();

        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> findByUserEmail(String email) {
        return jobApplicationRepository.findByUserEmail(email);
    }

    public JobApplication update(Long id, JobApplicationUpdateRequest request) {

        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ForbiddenException("Application not found"));

        application.setCompanyName(request.getCompanyName());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());

        return jobApplicationRepository.save(application);
    }
}
