package com.example.professor.controller;



import com.example.professor.dto.request.MajorRequest;
import com.example.professor.dto.request.ProfessorRequest;
import com.example.professor.dto.response.MajorResponse;
import com.example.professor.dto.response.ProfessorResponse;
import com.example.professor.global.response.LmsResponse;
import com.example.professor.servcie.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;


    //교수 정보 찾기.
    @GetMapping("/info/{id}")
    public LmsResponse<ProfessorResponse> findProfessor(@PathVariable("id") String id) {
        ProfessorResponse professor = professorService.findProfessor(id);
        return new LmsResponse<>(HttpStatus.OK, professor, "서비스 성공", "", LocalDateTime.now());
    }

    //교수 전공 찾기
    @GetMapping("{professorId}")
    public LmsResponse<List<MajorResponse>> findMajor(@PathVariable("professorId")String professorId) {
        List<MajorResponse> majorList = professorService.findMajor(professorId);
        return new LmsResponse<>(HttpStatus.OK, majorList, "서비스 성공", "", LocalDateTime.now());
    }


    //교수 정보 변경
    @PostMapping("/info")
    public LmsResponse<ProfessorResponse> updateProfessor(@RequestBody ProfessorRequest request){
        ProfessorResponse professorResponse = professorService.updateProfessor(request);
        return new LmsResponse<>(HttpStatus.OK, professorResponse, "서비스 성공", "", LocalDateTime.now());
    }


}
