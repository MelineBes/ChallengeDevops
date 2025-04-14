package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Utilisateur;
import edu.esiea.quiz_back.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;

    @Autowired
    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return repository.findAll();
    }

    public Optional<Utilisateur> getById(int id) {
        return repository.findById(id);
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(int id, Utilisateur utilisateurDetails) {
        Optional<Utilisateur> existing = repository.findById(id);

        if (existing.isPresent()) {
            Utilisateur utilisateur = existing.get();
            utilisateur.setNom(utilisateurDetails.getNom());
            return repository.save(utilisateur);
        } else {
            return null;
        }
    }

    public boolean deleteUtilisateur(int id) {
        Optional<Utilisateur> utilisateur = repository.findById(id);
        if (utilisateur.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
