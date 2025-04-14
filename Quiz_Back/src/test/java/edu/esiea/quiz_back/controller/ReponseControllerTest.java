package edu.esiea.quiz_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esiea.quiz_back.entity.Reponse;
import edu.esiea.quiz_back.service.ReponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReponseController.class)
class ReponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReponseService service;

    private Reponse reponse;

    @BeforeEach
    void setUp() {
        reponse = new Reponse("Paris", true, null);
        reponse.setId(1);
    }

    @Test
    void testGetAllReponses() throws Exception {
        when(service.getAllReponses()).thenReturn(Arrays.asList(reponse));

        mockMvc.perform(get("/reponses/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].texte").value("Paris"));
    }

    @Test
    void testGetReponseById() throws Exception {
        when(service.getReponseById(1)).thenReturn(Optional.of(reponse));

        mockMvc.perform(get("/reponses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.texte").value("Paris"));
    }

    @Test
    void testCreateReponse() throws Exception {
        when(service.createReponse(any(Reponse.class))).thenReturn(reponse);

        mockMvc.perform(post("/reponses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reponse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.texte").value("Paris"));
    }

    @Test
    void testUpdateReponse() throws Exception {
        when(service.updateReponse(eq(1), any(Reponse.class))).thenReturn(reponse);

        mockMvc.perform(put("/reponses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reponse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.texte").value("Paris"));
    }

    @Test
    void testDeleteReponse() throws Exception {
        when(service.deleteReponse(1)).thenReturn(true);

        mockMvc.perform(delete("/reponses/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
