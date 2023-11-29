package com.example.professor.dto.request;


import com.example.professor.entity.Professor;
import com.example.professor.entity.Status;
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
    private UUID id;
    private Status status;
    public Professor toEntity(){
        return Professor.builder()
                .id(id)
                .status(status)
                .build();
    }
}
