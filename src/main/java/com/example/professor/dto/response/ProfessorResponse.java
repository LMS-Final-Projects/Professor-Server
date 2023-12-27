package com.example.professor.dto.response;

import com.example.professor.entity.Professor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfessorResponse {

    private String id;
    private String professorName;
    private String professorNumber;
    private String email;
    private String phNumber;

    public ProfessorResponse(Professor professor) {
        this.id = professor.getId();
        this.professorName = professor.getProfessorName();
        this.email = professor.getEmail();
        this.phNumber = professor.getPhNumber();
    }

}
