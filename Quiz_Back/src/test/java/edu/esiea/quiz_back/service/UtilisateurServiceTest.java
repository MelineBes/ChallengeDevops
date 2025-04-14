package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Utilisateur;
import edu.esiea.quiz_back.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository repository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        utilisateur = new Utilisateur("John Doe", null);
    }

    @Test
    public void testCreateUtilisateur() {
        when(repository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);

        assertNotNull(createdUtilisateur);
        assertEquals("John Doe", createdUtilisateur.getNom());
        verify(repository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    public void testUpdateUtilisateur() {
        Utilisateur updatedUtilisateur = new Utilisateur("Jane Doe", null);
        when(repository.findById(1)).thenReturn(Optional.of(utilisateur));
        when(repository.save(any(Utilisateur.class))).thenReturn(updatedUtilisateur);

        Utilisateur result = utilisateurService.updateUtilisateur(1, updatedUtilisateur);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getNom());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    public void testDeleteUtilisateur() {
        when(repository.findById(1)).thenReturn(Optional.of(utilisateur));

        boolean isDeleted = utilisateurService.deleteUtilisateur(1);

        assertTrue(isDeleted);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void testGetById() {
        when(repository.findById(1)).thenReturn(Optional.of(utilisateur));

        Optional<Utilisateur> result = utilisateurService.getById(1);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNom());
    }

    @Test
    public void testGetAllUtilisateurs() {
        when(repository.findAll()).thenReturn(List.of(utilisateur));

        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();

        assertNotNull(utilisateurs);
        assertFalse(utilisateurs.isEmpty());
        assertEquals("John Doe", utilisateurs.get(0).getNom());
    }
}
