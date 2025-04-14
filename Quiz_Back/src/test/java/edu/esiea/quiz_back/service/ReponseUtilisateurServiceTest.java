package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.ReponseUtilisateur;
import edu.esiea.quiz_back.repository.ReponseUtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Permet à Mockito de gérer les mocks
public class ReponseUtilisateurServiceTest {

    @Mock
    private ReponseUtilisateurRepository reponseUtilisateurRepository;  // Mock du repository

    @InjectMocks
    private ReponseUtilisateurService reponseUtilisateurService;  // Injection du mock dans le service

    private ReponseUtilisateur reponse;

    @BeforeEach
    public void setUp() {
        reponse = new ReponseUtilisateur();
        reponse.setId(1);
        reponse.setCorrecte(true);
    }

    @Test
    public void testCreateReponse() {
        // Simuler le comportement de save
        when(reponseUtilisateurRepository.save(any(ReponseUtilisateur.class))).thenReturn(reponse);

        ReponseUtilisateur createdReponse = reponseUtilisateurService.createReponse(reponse);

        // Vérifier que la méthode save a bien été appelée
        verify(reponseUtilisateurRepository, times(1)).save(any(ReponseUtilisateur.class));

        // Vérifier que la réponse retournée est la bonne
        assertNotNull(createdReponse);
        assertEquals(1, createdReponse.getId());
        assertTrue(createdReponse.isCorrecte());
    }

    @Test
    public void testGetAllReponsesByUtilisateurId() {
        ReponseUtilisateur reponse1 = new ReponseUtilisateur();
        reponse1.setId(1);
        reponse1.setCorrecte(true);

        ReponseUtilisateur reponse2 = new ReponseUtilisateur();
        reponse2.setId(2);
        reponse2.setCorrecte(false);

        // Simuler le comportement de findByUtilisateurId
        when(reponseUtilisateurRepository.findByUtilisateurId(1)).thenReturn(Arrays.asList(reponse1, reponse2));

        // Appeler le service
        var reponses = reponseUtilisateurService.getAllReponsesByUtilisateurId(1);

        // Vérifier que la méthode findByUtilisateurId a bien été appelée
        verify(reponseUtilisateurRepository, times(1)).findByUtilisateurId(1);

        // Vérifier que la liste retournée contient les bonnes réponses
        assertNotNull(reponses);
        assertEquals(2, reponses.size());
        assertEquals(1, reponses.get(0).getId());
        assertEquals(2, reponses.get(1).getId());
    }

    @Test
    public void testGetReponseById() {
        // Simuler le comportement de findById
        when(reponseUtilisateurRepository.findById(1)).thenReturn(Optional.of(reponse));

        // Appeler le service
        Optional<ReponseUtilisateur> reponseRetrieved = reponseUtilisateurService.getReponseById(1);

        // Vérifier que la méthode findById a bien été appelée
        verify(reponseUtilisateurRepository, times(1)).findById(1);

        // Vérifier que la réponse retournée est celle attendue
        assertTrue(reponseRetrieved.isPresent());
        assertEquals(1, reponseRetrieved.get().getId());
    }


    @Test
    public void testDeleteReponse() {
        // Simuler le comportement de existsById
        when(reponseUtilisateurRepository.existsById(1)).thenReturn(true);

        // Appeler le service
        boolean result = reponseUtilisateurService.deleteReponse(1);

        // Vérifier que la méthode existsById a bien été appelée
        verify(reponseUtilisateurRepository, times(1)).existsById(1);

        // Vérifier que la méthode deleteById a bien été appelée
        verify(reponseUtilisateurRepository, times(1)).deleteById(1);

        // Vérifier que la suppression a bien fonctionné
        assertTrue(result);
    }

    @Test
    public void testCompterBonnesReponses() {
        // Simuler des réponses de l'utilisateur
        ReponseUtilisateur reponse1 = new ReponseUtilisateur();
        reponse1.setId(1);
        reponse1.setCorrecte(true);  // Bonne réponse

        ReponseUtilisateur reponse2 = new ReponseUtilisateur();
        reponse2.setId(2);
        reponse2.setCorrecte(false); // Mauvaise réponse

        // Simuler le comportement du repository
        when(reponseUtilisateurRepository.findByUtilisateurId(1)).thenReturn(Arrays.asList(reponse1, reponse2));

        // Appeler la méthode de service
        int bonnesReponses = reponseUtilisateurService.compterBonnesReponses(1);

        // Vérifier que le nombre de bonnes réponses est correct
        assertEquals(1, bonnesReponses);  // Il y a 1 bonne réponse
    }


}
