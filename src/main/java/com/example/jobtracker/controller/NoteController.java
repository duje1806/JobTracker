package com.example.jobtracker.controller;

import com.example.jobtracker.dto.NoteRequest;
import com.example.jobtracker.dto.NoteResponse;
import com.example.jobtracker.entity.Note;
import com.example.jobtracker.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-applications/{jobAppId}/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public NoteResponse addNote(
            @PathVariable Long jobAppId,
            @Valid @RequestBody NoteRequest request
    ) {
        Note note = noteService.addNote(jobAppId, request);

        return NoteResponse.builder()
                .id(note.getId())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }

    @GetMapping
    public List<NoteResponse> getNotes(@PathVariable Long jobAppId) {
        return noteService.getNotes(jobAppId)
                .stream()
                .map(n -> NoteResponse.builder()
                        .id(n.getId())
                        .content(n.getContent())
                        .createdAt(n.getCreatedAt())
                        .build()
                )
                .toList();
    }
}

