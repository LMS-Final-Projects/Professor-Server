package com.example.prfoessor.controller;

import com.example.global.exception.MethodException;
import com.example.student.controller.ManagerController;
import com.example.student.controller.StudentController;
import com.example.student.dto.request.StatusRequest;
import com.example.student.dto.request.StudentRequest;
import com.example.student.dto.response.StudentResponse;
import com.example.student.entity.Status;
import com.example.student.entity.Student;
import com.example.student.servcie.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ManagerController.class)
class ManagerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;


    @Nested
    class getAll {

        @Test
        void success() throws Exception {

            // Given
            Student student1 = new Student(UUID.randomUUID(), "채오성", 2023, 202317, "yrias8700@google.com", "010-6309-7402", Status.재학 ,List.of("math", "science"));
            Student student2 = new Student(UUID.randomUUID(), "김아영", 2023, 202317, "codhtjd@google.com", "010-6219-7402", Status.재학 ,List.of("math", "science"));


            List<StudentResponse> dto = Arrays.asList(
                    new StudentResponse(student1),
                    new StudentResponse(student2)
            );

            // When
            given(studentService.getAll()).willReturn(dto);

            // Then
            mockMvc.perform(get("/api/v1/manager/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(dto))
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(2))
                    .andExpect(jsonPath("$[0].name").value("채오성"))
                    .andExpect(jsonPath("$[1].name").value("김아영"))
                    .andDo(print());
        }

        @Test
        void fail() throws Exception {

            // Given
            Student student1 = new Student(UUID.randomUUID(), "채오성", 2023, 202317, "yrias8700@google.com", "010-6309-7402", Status.재학 ,List.of("math", "science"));
            Student student2 = new Student(UUID.randomUUID(), "김아영", 2023, 202317, "codhtjd@google.com", "010-6219-7402", Status.재학 ,List.of("math", "science"));


            List<StudentResponse> dto = Arrays.asList(
                    new StudentResponse(student1),
                    new StudentResponse(student2)
            );

            // When
            doThrow(new MethodException("조회 실패")).when(studentService).getAll();

            // Then
            mockMvc.perform(get("/api/v1/manager/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(dto))
                    )
                    .andExpect(status().isInternalServerError())
                    .andDo(print());

        }
    }

    @Nested
    class updateStatus {

        @Test
        void success() throws Exception {
            // Given
            StatusRequest dto = new StatusRequest(UUID.randomUUID(), Status.재학);

            // Response
            StudentResponse res = new StudentResponse(dto.toEntity());

            // When
            given(studentService.updateStatus(dto)).willReturn(res);

            // Then
            mockMvc.perform(post("/api/v1/manager/students/status")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(dto))
                    )
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @Test
        void fail() throws Exception {
            // Given
            StatusRequest dto = new StatusRequest(UUID.randomUUID(), Status.퇴학);

            // When
            doThrow(new MethodException("저장 기능 실패")).when(studentService).updateStatus(any(StatusRequest.class));

            // Then
            mockMvc.perform(post("/api/v1/manager/students/status")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(dto))
                    )
                    .andExpect(status().isInternalServerError())
                    .andDo(print());

        }
    }
}
