package com.example.professor.dto.request;


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
    private String id;
    private String professorName;
    private String email;
    private String phNumber;
    private List<String> majorNames;

}
