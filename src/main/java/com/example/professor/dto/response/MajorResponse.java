package com.example.professor.dto.response;

import com.example.professor.entity.Professor;
import com.example.professor.entity.ProfessorMajor;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorResponse {

    private String id;
    private String majorName;


}
