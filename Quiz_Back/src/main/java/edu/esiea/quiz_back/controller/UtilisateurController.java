package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Utilisateur;
import edu.esiea.quiz_back.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurCree = utilisateurService.createUtilisateur(utilisateur);
        return new ResponseEntity<>(utilisateurCree, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return updatedUtilisateur != null ? ResponseEntity.ok(updatedUtilisateur) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        return utilisateurService.deleteUtilisateur(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
