package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Reponse;
import edu.esiea.quiz_back.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponses")
@CrossOrigin
public class ReponseController {

    private final ReponseService service;

    @Autowired
    public ReponseController(ReponseService service) {
        this.service = service;
    }

    // Récupérer toutes les réponses
    @GetMapping("/all")
    public List<Reponse> getAllReponses() {
        return service.getAllReponses();
    }

    // Récupérer une réponse par son ID
    @GetMapping("/{id}")
    public Optional<Reponse> getReponseById(@PathVariable int id) {
        return service.getReponseById(id);
    }

    // Créer une nouvelle réponse
    @PostMapping
    public Reponse createReponse(@RequestBody Reponse reponse) {
        return service.createReponse(reponse);
    }

    // Mettre à jour une réponse existante
    @PutMapping("/{id}")
    public Reponse updateReponse(@PathVariable int id, @RequestBody Reponse reponse) {
        return service.updateReponse(id, reponse);
    }

    // Supprimer une réponse
    @DeleteMapping("/{id}")
    public boolean deleteReponse(@PathVariable int id) {
        return service.deleteReponse(id);
    }
}
