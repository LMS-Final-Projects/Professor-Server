package com.example.professor.dto.response;

import com.example.professor.entity.Professor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfessorResponse {

    private UUID id;
    private String professorName;
    private Integer year;
    private Integer professorNumber;
    private String email;
    private String phNumber;

    public ProfessorResponse(Professor professor) {
        this.id = professor.getId();
        this.professorName = professor.getProfessorName();
        this.year = professor.getYear();
        this.professorNumber = professor.getProfessorNumber();
        this.email = professor.getEmail();
        this.phNumber = professor.getPhNumber();
    }

}
