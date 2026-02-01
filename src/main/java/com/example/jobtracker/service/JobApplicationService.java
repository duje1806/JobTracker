package com.example.jobtracker.service;

import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.User;
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
}
