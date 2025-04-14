package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Question;
import edu.esiea.quiz_back.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {

    private final QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions() {
        try {
            return ResponseEntity.ok(service.getAllQuestions());
        }         catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body("Erreur : " + e.getMessage());
    }
    }

    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable int id) {
        return service.getQuestionById(id);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return service.createQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable int id, @RequestBody Question question) {
        return service.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestion(@PathVariable int id) {
        return service.deleteQuestion(id);
    }
}
