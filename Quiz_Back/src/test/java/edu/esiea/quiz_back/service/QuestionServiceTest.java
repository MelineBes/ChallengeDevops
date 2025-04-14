package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Question;
import edu.esiea.quiz_back.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions() {
        List<Question> questions = List.of(new Question(), new Question());
        when(questionRepository.findAll()).thenReturn(questions);

        List<Question> result = questionService.getAllQuestions();

        assertEquals(2, result.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void testGetQuestionById_Found() {
        Question question = new Question();
        question.setId(1);
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        Optional<Question> result = questionService.getQuestionById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        verify(questionRepository).findById(1);
    }

    @Test
    void testCreateQuestion() {
        Question newQuestion = new Question();
        newQuestion.setIntitule("Sample");

        when(questionRepository.save(newQuestion)).thenReturn(newQuestion);

        Question result = questionService.createQuestion(newQuestion);

        assertEquals("Sample", result.getIntitule());
        verify(questionRepository, times(1)).save(newQuestion);
    }

    @Test
    void testUpdateQuestion_Found() {
        Question existing = new Question();
        existing.setId(1);
        existing.setIntitule("Old");

        Question updated = new Question();
        updated.setIntitule("New");

        when(questionRepository.findById(1)).thenReturn(Optional.of(existing));
        when(questionRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Question result = questionService.updateQuestion(1, updated);

        assertEquals("New", result.getIntitule());
        verify(questionRepository).findById(1);
        verify(questionRepository).save(existing);
    }

    @Test
    void testDeleteQuestion_Found() {
        when(questionRepository.findById(1)).thenReturn(Optional.of(new Question()));

        boolean result = questionService.deleteQuestion(1);

        assertTrue(result);
        verify(questionRepository).deleteById(1);
    }
}
