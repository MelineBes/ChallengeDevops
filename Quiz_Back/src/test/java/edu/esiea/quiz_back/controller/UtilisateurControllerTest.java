package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Utilisateur;
import edu.esiea.quiz_back.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UtilisateurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        // Initialise les mocks
        MockitoAnnotations.openMocks(this);

        // Crée le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();

        // Crée un objet Utilisateur pour les tests
        utilisateur = new Utilisateur("John Doe", null);
    }

    @Test
    public void testGetAllUtilisateurs() throws Exception {
        when(utilisateurService.getAllUtilisateurs()).thenReturn(List.of(utilisateur));

        mockMvc.perform(get("/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("John Doe"));
    }

    @Test
    public void testGetUtilisateurById() throws Exception {
        when(utilisateurService.getById(1)).thenReturn(Optional.of(utilisateur));

        mockMvc.perform(get("/utilisateurs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"));
    }

    @Test
    public void testCreateUtilisateur() throws Exception {
        when(utilisateurService.createUtilisateur(any(Utilisateur.class))).thenReturn(utilisateur);

        mockMvc.perform(post("/utilisateurs")
                        .contentType("application/json")
                        .content("{\"nom\":\"John Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("John Doe"));
    }

    @Test
    public void testUpdateUtilisateur() throws Exception {
        // Création d'un utilisateur avec le nom mis à jour
        Utilisateur utilisateurMiseAJour = new Utilisateur("Jane Doe", null);

        // Simulation du service pour qu'il retourne l'utilisateur mis à jour
        when(utilisateurService.updateUtilisateur(eq(1), any(Utilisateur.class))).thenReturn(utilisateurMiseAJour);

        mockMvc.perform(put("/utilisateurs/1")
                        .contentType("application/json")
                        .content("{\"nom\":\"Jane Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Jane Doe"));
    }

    @Test
    public void testDeleteUtilisateur() throws Exception {
        when(utilisateurService.deleteUtilisateur(1)).thenReturn(true);

        mockMvc.perform(delete("/utilisateurs/1"))
                .andExpect(status().isNoContent());
    }
}
