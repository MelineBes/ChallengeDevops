package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.ReponseUtilisateur;
import edu.esiea.quiz_back.service.ReponseUtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReponseUtilisateurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReponseUtilisateurService reponseUtilisateurService;

    @InjectMocks
    private ReponseUtilisateurController reponseUtilisateurController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reponseUtilisateurController).build();
    }

    @Test
    public void testGetAllReponsesByUtilisateur() throws Exception {
        ReponseUtilisateur reponse1 = new ReponseUtilisateur();
        reponse1.setId(1);
        reponse1.setCorrecte(true);

        ReponseUtilisateur reponse2 = new ReponseUtilisateur();
        reponse2.setId(2);
        reponse2.setCorrecte(false);

        when(reponseUtilisateurService.getAllReponsesByUtilisateurId(1))
                .thenReturn(Arrays.asList(reponse1, reponse2));

        mockMvc.perform(get("/reponses-utilisateur/utilisateur/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testGetAllReponsesByUtilisateurNoContent() throws Exception {
        when(reponseUtilisateurService.getAllReponsesByUtilisateurId(1))
                .thenReturn(Arrays.asList());

        mockMvc.perform(get("/reponses-utilisateur/utilisateur/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetReponseById() throws Exception {
        ReponseUtilisateur reponse = new ReponseUtilisateur();
        reponse.setId(1);
        reponse.setCorrecte(true);

        when(reponseUtilisateurService.getReponseById(1))
                .thenReturn(Optional.of(reponse));

        mockMvc.perform(get("/reponses-utilisateur/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetReponseByIdNotFound() throws Exception {
        when(reponseUtilisateurService.getReponseById(1))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/reponses-utilisateur/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateReponse() throws Exception {
        ReponseUtilisateur newReponse = new ReponseUtilisateur();
        newReponse.setId(1);
        newReponse.setCorrecte(true);

        when(reponseUtilisateurService.createReponse(any(ReponseUtilisateur.class)))
                .thenReturn(newReponse);

        mockMvc.perform(post("/reponses-utilisateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"utilisateur\": null, \"question\": null, \"reponse\": null, \"correcte\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correcte").value(true));
    }

    @Test
    public void testDeleteReponse() throws Exception {
        when(reponseUtilisateurService.deleteReponse(1))
                .thenReturn(true);

        mockMvc.perform(delete("/reponses-utilisateur/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteReponseNotFound() throws Exception {
        when(reponseUtilisateurService.deleteReponse(1))
                .thenReturn(false);

        mockMvc.perform(delete("/reponses-utilisateur/1"))
                .andExpect(status().isNotFound());
    }
}
