package com.example.professor.controller;


import com.example.global.response.LmsResponse;
import com.example.professor.dto.request.ProfessorRequest;
import com.example.professor.dto.response.ProfessorResponse;
import com.example.professor.servcie.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    //교수 정보 저장.
    @PostMapping
    public LmsResponse<Void> saveProfessor(@RequestBody ProfessorRequest request){
        professorService.saveProfessor(request);
        return new LmsResponse<>(HttpStatus.CREATED, null, "서비스 성공", "", LocalDateTime.now());
    }

    //교수 정보 찾기.
    @GetMapping("/info")
    public LmsResponse<ProfessorResponse> findProfessor(@RequestBody ProfessorRequest request) {
        ProfessorResponse professor = professorService.findProfessor(request);
        return new LmsResponse<>(HttpStatus.OK, professor, "서비스 성공", "", LocalDateTime.now());
    }


    //교수 정보 변경
    @PostMapping("/info")
    public LmsResponse<ProfessorResponse> updateProfessor(@RequestBody ProfessorRequest request){
        ProfessorResponse professorResponse = professorService.updateProfessor(request);
        return new LmsResponse<>(HttpStatus.OK, professorResponse, "서비스 성공", "", LocalDateTime.now());
    }

}