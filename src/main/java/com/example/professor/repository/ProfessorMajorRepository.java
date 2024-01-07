package com.example.professor.repository;


import com.example.professor.entity.Professor;
import com.example.professor.entity.ProfessorMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorMajorRepository extends JpaRepository<ProfessorMajor, String> {
    @Query("SELECT p from ProfessorMajor as p where p.professorId = :id")
    List<ProfessorMajor> findByProfessorId(@Param("id")String id);
}
