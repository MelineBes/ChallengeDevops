package edu.esiea.quiz_back.repository;

import edu.esiea.quiz_back.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}

