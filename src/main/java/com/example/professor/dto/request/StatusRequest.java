package com.example.professor.dto.request;


import com.example.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusRequest {
    private String id;
    private String status;
    public Professor toEntity(){
        return Professor.builder()
                .id(id)
                .status(status)
                .build();
    }
}
