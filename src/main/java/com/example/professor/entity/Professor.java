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

    @Id
    private String id;
    @Column(nullable = false)
    private String professorName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phNumber;
    @Column(nullable = false)
    private String majorList;
    @Column(nullable = false)
    private String status;

}

