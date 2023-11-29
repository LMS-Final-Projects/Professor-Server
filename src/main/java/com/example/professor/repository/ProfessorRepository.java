package com.example.professor.repository;


import com.example.professor.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    @Query("SELECT p from Professor as p where p.id = :id")
    Optional<Professor> findByProfessorId(@Param("id")UUID id);
}
