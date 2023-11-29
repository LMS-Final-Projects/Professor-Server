package com.example.professor.dto.request;


import com.example.professor.entity.Status;
import com.example.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorRequest {
    private UUID id;
    private String professorName;
    private Integer year;
    private int professorNumber;
    private String email;
    private String phNumber;
    private List<String> majorNames;

    public Professor toEntity(){
        return Professor.builder()
                .id(id)
                .professorName(professorName)
                .year(year)
                .professorNumber(professorNumber)
                .email(email)
                .phNumber(phNumber)
                .status(Status.재학)
                .build();
    }
}
