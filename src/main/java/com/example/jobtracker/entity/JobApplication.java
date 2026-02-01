package com.example.jobtracker.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String companyName;

    @Column(nullable = false)
    @NotBlank
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private ApplicationStatus status;

    @Column(nullable = false)
    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "jobApplication")
    private List<Note> notes;
}
