package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Question;
import edu.esiea.quiz_back.entity.Quiz;
import edu.esiea.quiz_back.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    @Autowired
    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Optional<Question> getQuestionById(int id) {
        return repository.findById(id);
    }

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    public Question updateQuestion(int id, Question questionDetails) {
        Optional<Question> existing = repository.findById(id);
        if (existing.isPresent()) {
            Question q = existing.get();
            q.setIntitule(questionDetails.getIntitule());
            q.setQuiz(questionDetails.getQuiz());
            q.setReponses(questionDetails.getReponses());
            return repository.save(q);
        }
        return null;
    }

    public boolean deleteQuestion(Integer id) {
        Optional<Question> question = repository.findById(id);
        if (question.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
