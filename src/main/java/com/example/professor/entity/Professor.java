package com.example.professor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String professorName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phNumber;
    @Enumerated(EnumType.STRING)
    private Status status;

}

