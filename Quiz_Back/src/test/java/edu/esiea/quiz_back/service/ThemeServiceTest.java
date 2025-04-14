package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Theme;
import edu.esiea.quiz_back.repository.ThemeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ThemeServiceTest {

    private ThemeRepository repository;
    private ThemeService service;

    private Theme theme;

    @BeforeEach
    void setUp() {
        repository = mock(ThemeRepository.class);
        service = new ThemeService(repository);

        theme = new Theme();
        theme.setId(1);
        theme.setName("Géographie");
        theme.setQuizz(null);
    }

    @Test
    void testGetAllThemes() {
        when(repository.findAll()).thenReturn(Arrays.asList(theme));

        List<Theme> result = service.getAllThemes();

        assertEquals(1, result.size());
        assertEquals("Géographie", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        when(repository.findById(1)).thenReturn(Optional.of(theme));

        Optional<Theme> result = service.getById(1);

        assertTrue(result.isPresent());
        assertEquals("Géographie", result.get().getName());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testCreateTheme() {
        when(repository.save(theme)).thenReturn(theme);

        Theme created = service.createTheme(theme);

        assertEquals("Géographie", created.getName());
        verify(repository, times(1)).save(theme);
    }

    @Test
    void testUpdateTheme_Found() {
        Theme updatedData = new Theme();
        updatedData.setName("Histoire");
        updatedData.setQuizz(null);

        when(repository.findById(1)).thenReturn(Optional.of(theme));
        when(repository.save(any(Theme.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Theme result = service.updateTheme(1, updatedData);

        assertNotNull(result);
        assertEquals("Histoire", result.getName());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(any(Theme.class));
    }

    @Test
    void testDeleteTheme_Found() {
        when(repository.findById(1)).thenReturn(Optional.of(theme));
        doNothing().when(repository).deleteById(1);

        boolean result = service.deleteTheme(1);

        assertTrue(result);
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).deleteById(1);
    }
}
