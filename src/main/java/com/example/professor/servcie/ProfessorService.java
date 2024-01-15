package com.example.professor.servcie;

import com.example.professor.dto.request.MajorRequest;
import com.example.professor.dto.request.ProfessorRequest;
import com.example.professor.dto.request.StatusRequest;
import com.example.professor.dto.response.MajorResponse;
import com.example.professor.dto.response.ProfessorResponse;
import com.example.professor.entity.Professor;
import com.example.professor.entity.ProfessorMajor;
import com.example.professor.global.exception.MethodException;
import com.example.professor.global.exception.NotFoundException;
import com.example.professor.repository.ProfessorMajorRepository;
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
    private final ProfessorMajorRepository professorMajorRepository;

    //교수 정보 찾기.
    @Transactional
    public ProfessorResponse findProfessor(String memberId) {

        Professor professor = repository.findByProfessorId(memberId).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        ProfessorResponse dto = new ProfessorResponse(professor);
        return dto;
    }

    //전공 찾기
    @Transactional
    public List<MajorResponse> findMajor(String professorId) {

        List<ProfessorMajor> byProfessorId = professorMajorRepository.findByProfessorId(professorId);
        List<MajorResponse> majorResponseList = new ArrayList<>();

        for (ProfessorMajor professorMajor : byProfessorId) {

            MajorResponse majorResponse = new MajorResponse();

            majorResponse.setId(professorMajor.getId());
            majorResponse.setMajorName(professorMajor.getMajorName());

            majorResponseList.add(majorResponse);
        }

        return majorResponseList;
    }


    //교수 정보 변경.
    @Transactional
    public ProfessorResponse updateProfessor(ProfessorRequest request) {
        Professor professor = repository.findByProfessorId(request.getId()).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        Professor save = repository.save(professor);
        ProfessorResponse dto = new ProfessorResponse(save);
        return dto;
    }

    //교수 상태 변경
    @Transactional
    public ProfessorResponse updateStatus(StatusRequest request) {
        Professor professor = repository.findByProfessorId(request.getId()).orElseThrow(() -> new NotFoundException("해당 유저가 없습니다."));
        ProfessorResponse dto = new ProfessorResponse(professor);
        return dto;
    }


    //교수 전체 조회.
    @Transactional
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
