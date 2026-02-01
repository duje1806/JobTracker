package com.example.jobtracker.service;

import com.example.jobtracker.dto.JobApplicationUpdateRequest;
import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.exception.ResourceNotFoundException;
import com.example.jobtracker.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplication create(JobApplication application, User user){
        application.setUser(user);
        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> findByUser(Long userId){
        return jobApplicationRepository.findByUserId(userId);
    }
    public JobApplication update(Long id, JobApplicationUpdateRequest request) {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        application.setCompanyName(request.getCompanyName());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());

        return jobApplicationRepository.save(application);
    }
}
