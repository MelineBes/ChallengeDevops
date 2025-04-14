package edu.esiea.quiz_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esiea.quiz_back.entity.Question;
import edu.esiea.quiz_back.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuestionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllQuestions() throws Exception {
        List<Question> questions = Arrays.asList(new Question(), new Question());
        when(service.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(get("/questions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAllQuestionsWithException() throws Exception {
        when(service.getAllQuestions()).thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/questions/all"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur : Test exception"));
    }

    @Test
    void testGetQuestionById() throws Exception {
        Question q = new Question();
        q.setId(1);
        q.setIntitule("Exemple");

        when(service.getQuestionById(1)).thenReturn(Optional.of(q));

        mockMvc.perform(get("/questions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.intitule").value("Exemple"));
    }

    @Test
    void testCreateQuestion() throws Exception {
        Question q = new Question();
        q.setIntitule("Nouvelle");

        when(service.createQuestion(any())).thenReturn(q);

        mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(q)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.intitule").value("Nouvelle"));
    }

    @Test
    void testUpdateQuestion() throws Exception {
        Question updated = new Question();
        updated.setIntitule("Mis à jour");

        when(service.updateQuestion(eq(1), any())).thenReturn(updated);

        mockMvc.perform(put("/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.intitule").value("Mis à jour"));
    }

    @Test
    void testDeleteQuestion() throws Exception {
        when(service.deleteQuestion(1)).thenReturn(true);

        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
