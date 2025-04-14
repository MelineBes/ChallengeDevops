package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Question;
import edu.esiea.quiz_back.entity.Reponse;
import edu.esiea.quiz_back.repository.ReponseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReponseServiceTest {

    private ReponseRepository repository;
    private ReponseService service;

    @BeforeEach
    void setUp() {
        repository = mock(ReponseRepository.class);
        service = new ReponseService(repository);
    }

    @Test
    void testGetAllReponses() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Reponse(), new Reponse()));
        assertEquals(2, service.getAllReponses().size());
    }

    @Test
    void testGetReponseById() {
        Reponse r = new Reponse("Oui", true, null);
        when(repository.findById(1)).thenReturn(Optional.of(r));

        Optional<Reponse> result = service.getReponseById(1);
        assertTrue(result.isPresent());
        assertEquals("Oui", result.get().getTexte());
    }

    @Test
    void testCreateReponse() {
        Reponse r = new Reponse("Non", false, null);
        when(repository.save(ArgumentMatchers.any(Reponse.class))).thenReturn(r);

        Reponse saved = service.createReponse(r);
        assertEquals("Non", saved.getTexte());
    }

    @Test
    void testUpdateReponse() {
        Question q = new Question();
        Reponse existing = new Reponse("Ancien", false, q);
        when(repository.findById(1)).thenReturn(Optional.of(existing));

        Reponse updated = new Reponse("Nouveau", true, q);
        when(repository.save(any(Reponse.class))).thenReturn(updated);

        Reponse result = service.updateReponse(1, updated);

        assertNotNull(result);
        assertEquals("Nouveau", result.getTexte());
        assertTrue(result.isCorrecte());
    }

    @Test
    void testUpdateReponse_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        Reponse result = service.updateReponse(1, new Reponse());
        assertNull(result);
    }

    @Test
    void testDeleteReponse() {
        Reponse r = new Reponse("Test", true, null);
        when(repository.findById(1)).thenReturn(Optional.of(r));
        doNothing().when(repository).deleteById(1);

        boolean deleted = service.deleteReponse(1);
        assertTrue(deleted);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteReponse_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        boolean deleted = service.deleteReponse(1);
        assertFalse(deleted);
    }
}
