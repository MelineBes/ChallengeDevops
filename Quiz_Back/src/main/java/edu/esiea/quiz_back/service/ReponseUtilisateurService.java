package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.ReponseUtilisateur;
import edu.esiea.quiz_back.repository.ReponseUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseUtilisateurService {

    private final ReponseUtilisateurRepository reponseUtilisateurRepository;

    @Autowired
    public ReponseUtilisateurService(ReponseUtilisateurRepository reponseUtilisateurRepository) {
        this.reponseUtilisateurRepository = reponseUtilisateurRepository;
    }

    // Créer une réponse utilisateur
    public ReponseUtilisateur createReponse(ReponseUtilisateur reponseUtilisateur) {
        return reponseUtilisateurRepository.save(reponseUtilisateur);
    }

    // Récupérer toutes les réponses d'un utilisateur
    public List<ReponseUtilisateur> getAllReponsesByUtilisateurId(int utilisateurId) {
        return reponseUtilisateurRepository.findByUtilisateurId(utilisateurId);
    }

    // Récupérer une réponse spécifique
    public Optional<ReponseUtilisateur> getReponseById(int id) {
        return reponseUtilisateurRepository.findById(id);
    }

    // Supprimer une réponse
    public boolean deleteReponse(int id) {
        if (reponseUtilisateurRepository.existsById(id)) {
            reponseUtilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
