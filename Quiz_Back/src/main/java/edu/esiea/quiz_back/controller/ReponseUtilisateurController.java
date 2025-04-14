package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.ReponseUtilisateur;
import edu.esiea.quiz_back.service.ReponseUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponses-utilisateur")
public class ReponseUtilisateurController {

    private final ReponseUtilisateurService reponseUtilisateurService;

    @Autowired
    public ReponseUtilisateurController(ReponseUtilisateurService reponseUtilisateurService) {
        this.reponseUtilisateurService = reponseUtilisateurService;
    }

    // Récupérer toutes les réponses d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<ReponseUtilisateur>> getAllReponsesByUtilisateur(@PathVariable int utilisateurId) {
        List<ReponseUtilisateur> reponses = reponseUtilisateurService.getAllReponsesByUtilisateurId(utilisateurId);
        if (reponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    // Récupérer une réponse spécifique
    @GetMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> getReponseById(@PathVariable int id) {
        Optional<ReponseUtilisateur> reponse = reponseUtilisateurService.getReponseById(id);
        return reponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle réponse utilisateur
    @PostMapping
    public ResponseEntity<ReponseUtilisateur> createReponse(@RequestBody ReponseUtilisateur reponseUtilisateur) {
        ReponseUtilisateur createdReponse = reponseUtilisateurService.createReponse(reponseUtilisateur);
        return new ResponseEntity<>(createdReponse, HttpStatus.CREATED);
    }

    // Supprimer une réponse utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable int id) {
        if (reponseUtilisateurService.deleteReponse(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
