package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Quiz;
import edu.esiea.quiz_back.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuizzes() {
        List<Quiz> mockList = List.of(new Quiz(), new Quiz());
        when(quizRepository.findAll()).thenReturn(mockList);

        List<Quiz> result = quizService.getAllQuizzes();

        assertEquals(2, result.size());
        verify(quizRepository, times(1)).findAll();
    }

    @Test
    void testGetById_ExistingId() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));

        Optional<Quiz> result = quizService.getById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testCreateQuiz() {
        Quiz newQuiz = new Quiz();
        newQuiz.setTitre("Sample");

        when(quizRepository.save(newQuiz)).thenReturn(newQuiz);

        Quiz result = quizService.createQuiz(newQuiz);

        assertEquals("Sample", result.getTitre());
        verify(quizRepository, times(1)).save(newQuiz);
    }

    @Test
    void testUpdateQuiz_Found() {
        Quiz oldQuiz = new Quiz();
        oldQuiz.setId(1);
        oldQuiz.setTitre("Old");

        Quiz updatedQuiz = new Quiz();
        updatedQuiz.setTitre("New");

        when(quizRepository.findById(1)).thenReturn(Optional.of(oldQuiz));
        when(quizRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Quiz result = quizService.updateQuiz(1, updatedQuiz);

        assertEquals("New", result.getTitre());
        verify(quizRepository).findById(1);
        verify(quizRepository).save(oldQuiz);
    }

    @Test
    void testDeleteQuiz_Found() {
        when(quizRepository.findById(1)).thenReturn(Optional.of(new Quiz()));

        boolean result = quizService.deleteQuiz(1);

        assertTrue(result);
        verify(quizRepository).deleteById(1);
    }
}
