package com.example.professor.controller;


import com.example.global.response.LmsResponse;
import com.example.professor.dto.request.StatusRequest;
import com.example.professor.dto.response.ProfessorResponse;
import com.example.professor.servcie.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manager/professors")
@RequiredArgsConstructor
public class ManagerController {

    private final ProfessorService professorService;


    //전체 교수 조회
    @GetMapping
    public LmsResponse<List<ProfessorResponse>> getAll(){

        List<ProfessorResponse> all = professorService.getAll();
        return new LmsResponse<>(HttpStatus.OK, all, "서비스 성공", "", LocalDateTime.now());
    };


    //교수 상태 관리
    @PostMapping("/status")
    public LmsResponse<ProfessorResponse> updateStatus(@RequestBody StatusRequest request){
        ProfessorResponse professorResponse = professorService.updateStatus(request);
        return new LmsResponse<>(HttpStatus.OK, professorResponse, "서비스 성공", "", LocalDateTime.now());

    }

}
