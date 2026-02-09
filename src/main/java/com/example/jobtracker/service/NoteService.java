package com.example.jobtracker.service;

import com.example.jobtracker.dto.NoteRequest;
import com.example.jobtracker.entity.JobApplication;
import com.example.jobtracker.entity.Note;
import com.example.jobtracker.exception.ResourceNotFoundException;
import com.example.jobtracker.repository.JobApplicationRepository;
import com.example.jobtracker.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public Note addNote(Long jobAppId, NoteRequest request) {

        JobApplication jobApp = jobApplicationRepository.findById(jobAppId)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        Note note = Note.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .jobApplication(jobApp)
                .build();

        return noteRepository.save(note);
    }

    public List<Note> getNotes(Long jobAppId) {
        return noteRepository.findByJobApplicationId(jobAppId);
    }
}

