package edu.esiea.quiz_back.repository;

import edu.esiea.quiz_back.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}

