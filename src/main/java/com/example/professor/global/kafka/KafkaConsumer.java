package com.example.professor.global.kafka;


import com.example.professor.entity.Professor;
import com.example.professor.entity.ProfessorMajor;
import com.example.professor.repository.ProfessorMajorRepository;
import com.example.professor.repository.ProfessorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProfessorRepository professorRepository;
    private final ProfessorMajorRepository professorMajorRepository;

    @KafkaListener(topics = "member", groupId = "professor_1")
    public void professorListener(String kafkaMessage) {
        Map<Object, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            String action = (String) map.get("kafkaAction");
            if (action.equals(KafkaAction.CREATE.name())) {
                String role = (String) map.get("role");
                if ("PROFESSOR".equals(role)) {
                    Professor save = Professor.builder()
                            .id((String) map.get("id"))
                            .professorName((String) map.get("name"))
                            .phNumber((String) map.get("phNumber"))
                            .email((String) map.get("email"))
                            .majorList((String) map.get("majorList"))
                            .status((String) map.get("status"))
                            .build();
                    professorRepository.save(save);

                    System.out.println(map);
                } else {
                    System.out.println("Skipping Professor save due to invalid role: " + map);
                }
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    @KafkaListener(topics = "major", groupId = "professor_1")
    public void majorListener(String kafkaMessage) {
        Map<Object, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            String action = (String) map.get("kafkaAction");
            if (action.equals(KafkaAction.CREATE.name())) {
                String role = (String) map.get("role");
                if ("PROFESSOR".equals(role)) {
                    ProfessorMajor build = ProfessorMajor.builder()
                            .id((String) map.get("id"))
                            .professorId((String) map.get("memberId"))
                            .majorName((String) map.get("majorName"))
                            .checkMajor((String) map.get("checkMajor"))
                            .build();
                    professorMajorRepository .save(build);
                    System.out.println(map);
                } else {
                    System.out.println("Skipping Student save due to invalid role: " + map);
                }
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
}

