package com.example.professor.servcie;


import com.example.global.exception.MethodException;
import com.example.global.exception.NotFoundException;
import com.example.professor.dto.request.StatusRequest;
import com.example.professor.dto.request.ProfessorRequest;
import com.example.professor.dto.response.ProfessorResponse;
import com.example.professor.entity.Professor;
import com.example.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    //교수 회원가입 할때, 정보 저장.
    @Transactional
    public void saveProfessor(ProfessorRequest request) {

        try {
            repository.save(request.toEntity());
        } catch (Exception e) {
            throw new MethodException("회원가입 정보 저장 실패");
        }
    }

    //교수 정보 찾기.
    public ProfessorResponse findProfessor(ProfessorRequest request) {

        Professor professor = repository.findByProfessorId(request.getId()).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        ProfessorResponse dto = new ProfessorResponse(professor);
        return dto;
    }


    //교수 정보 변경.
    public ProfessorResponse updateProfessor(ProfessorRequest request) {
        Professor professor = repository.findByProfessorId(request.getId()).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        repository.findByProfessorId(request.getId());
        ProfessorResponse dto = new ProfessorResponse(professor);
        return dto;
    }

    //교수 상태 변경
    public ProfessorResponse updateStatus(StatusRequest request) {
        Professor professor = repository.findByProfessorId(request.getId()).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        repository.findByProfessorId(request.getId());
        ProfessorResponse dto = new ProfessorResponse(professor);
        return dto;
    }


    //교수 전체 조회.
    public List<ProfessorResponse> getAll() {
        List<ProfessorResponse> dtoList = new ArrayList<>();
        try {
            List<Professor> all = repository.findAll();

            for (Professor professor : all) {
                ProfessorResponse response = new ProfessorResponse(professor);
                dtoList.add(response);
            }

            return dtoList;
        } catch (Exception e) {
            throw new MethodException("교수 조회 실패");
        }
    }
}
