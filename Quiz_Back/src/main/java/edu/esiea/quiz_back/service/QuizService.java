package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Quiz;
import edu.esiea.quiz_back.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    // Récupération de tous les quiz
    public List<Quiz> getAllQuizzes() {
        return repository.findAll();
    }

    // Récupérer un quiz par ID
    public Optional<Quiz> getById(int id) {
        return repository.findById(id);
    }

    // Création d'un nouveau quiz
    public Quiz createQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    // Mise à jour d'un quiz existant
    public Quiz updateQuiz(Integer id, Quiz quizDetails) {
        Optional<Quiz> existingQuiz = repository.findById(id);

        if (existingQuiz.isPresent()) {
            Quiz quiz = existingQuiz.get();
            // Ici, tu mets à jour les propriétés du quiz existant
            quiz.setTitre(quizDetails.getTitre());
            quiz.setTheme(quizDetails.getTheme());
            quiz.setQuestions(quizDetails.getQuestions());
            return repository.save(quiz);
        } else {
            return null;
        }
    }

    // Suppression d'un quiz
    public boolean deleteQuiz(Integer id) {
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
