package edu.esiea.quiz_back.repository;

import edu.esiea.quiz_back.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Integer> {

}