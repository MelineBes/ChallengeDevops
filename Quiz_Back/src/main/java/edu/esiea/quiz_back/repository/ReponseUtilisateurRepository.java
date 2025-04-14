package edu.esiea.quiz_back.repository;

import edu.esiea.quiz_back.entity.ReponseUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReponseUtilisateurRepository extends JpaRepository<ReponseUtilisateur, Integer> {

    List<ReponseUtilisateur> findByUtilisateurId(int utilisateurId);
}