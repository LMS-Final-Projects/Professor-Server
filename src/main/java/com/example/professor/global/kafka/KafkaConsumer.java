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
        ObjectMapper mapper = new ObjectMapper();
        try {
            List< Map<Object, Object>> majorList = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            for ( Map<Object, Object> professorMajor : majorList) {
                String action = (String) professorMajor.get("kafkaAction");
                if (action.equals(KafkaAction.CREATE.name())) {
                    String role =  (String) professorMajor.get("role");
                    if ("PROFESSOR".equals(role)) {
                        ProfessorMajor build = ProfessorMajor.builder()
                                .id((String) professorMajor.get("id"))
                                .professorId((String) professorMajor.get("professorId"))
                                .majorName((String) professorMajor.get("majorName"))
                                .checkMajor((String) professorMajor.get("checkMajor"))
                                .build();
                        professorMajorRepository.save(build);
                        System.out.println(professorMajor);
                    } else {
                        System.out.println("Skipping Professor save due to invalid role: " + professorMajor);
                    }
                }
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
}

