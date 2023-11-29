package com.example.professor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ProfessorMajor {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID professorId;

    private Long majorId;

    private String majorName;


}
