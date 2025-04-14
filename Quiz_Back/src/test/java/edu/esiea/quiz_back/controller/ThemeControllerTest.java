package edu.esiea.quiz_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esiea.quiz_back.entity.Theme;
import edu.esiea.quiz_back.service.ThemeService;
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

@WebMvcTest(ThemeController.class)
class ThemeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ThemeService service;

    private Theme theme;

    @BeforeEach
    void setUp() {
        theme = new Theme("Culture Générale", null);
        theme.setId(1);
    }

    @Test
    void testGetAllThemes() throws Exception {
        when(service.getAllThemes()).thenReturn(Arrays.asList(theme));

        mockMvc.perform(get("/themes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Culture Générale"));
    }

    @Test
    void testGetThemeById() throws Exception {
        when(service.getById(1)).thenReturn(Optional.of(theme));

        mockMvc.perform(get("/themes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Culture Générale"));
    }

    @Test
    void testCreateTheme() throws Exception {
        when(service.createTheme(any(Theme.class))).thenReturn(theme);

        mockMvc.perform(post("/themes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theme)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Culture Générale"));
    }

    @Test
    void testUpdateTheme() throws Exception {
        when(service.updateTheme(eq(1), any(Theme.class))).thenReturn(theme);

        mockMvc.perform(put("/themes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theme)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Culture Générale"));
    }

    @Test
    void testDeleteTheme() throws Exception {
        when(service.deleteTheme(1)).thenReturn(true);

        mockMvc.perform(delete("/themes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
