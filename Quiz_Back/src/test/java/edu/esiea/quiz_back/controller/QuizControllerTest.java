package edu.esiea.quiz_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esiea.quiz_back.entity.Quiz;
import edu.esiea.quiz_back.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuizController.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuizService quizService;

    private Quiz quiz;

    @BeforeEach
    public void setUp() {
        quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitre("Test Quiz");
    }

    @Test
    public void testGetAllQuizzes() throws Exception {
        when(quizService.getAllQuizzes()).thenReturn(Arrays.asList(quiz));

        mockMvc.perform(get("/quiz/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titre").value("Test Quiz"));
    }

    @Test
    public void testGetQuizById() throws Exception {
        when(quizService.getById(1)).thenReturn(Optional.of(quiz));

        mockMvc.perform(get("/quiz/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titre").value("Test Quiz"));
    }

    @Test
    public void testCreateQuiz() throws Exception {
        when(quizService.createQuiz(any(Quiz.class))).thenReturn(quiz);

        mockMvc.perform(post("/quiz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quiz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titre").value("Test Quiz"));
    }

    @Test
    public void testUpdateQuiz() throws Exception {
        quiz.setTitre("Updated Quiz");
        when(quizService.updateQuiz(eq(1), any(Quiz.class))).thenReturn(quiz);

        mockMvc.perform(put("/quiz/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quiz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Updated Quiz"));
    }

    @Test
    public void testDeleteQuiz() throws Exception {
        when(quizService.deleteQuiz(1)).thenReturn(true);

        mockMvc.perform(delete("/quiz/1"))
                .andExpect(status().isNoContent());
    }
}
