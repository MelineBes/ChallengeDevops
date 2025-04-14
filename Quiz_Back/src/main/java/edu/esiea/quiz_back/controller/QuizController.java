package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Quiz;
import edu.esiea.quiz_back.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuiz() {
        try {
            return ResponseEntity.ok(service.getAllQuizzes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        Optional<Quiz> quiz = service.getById(id);
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = service.createQuiz(quiz);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer id, @RequestBody Quiz quizDetails) {
        Quiz updatedQuiz = service.updateQuiz(id, quizDetails);
        return updatedQuiz != null ? new ResponseEntity<>(updatedQuiz, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer id) {
        boolean isDeleted = service.deleteQuiz(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
