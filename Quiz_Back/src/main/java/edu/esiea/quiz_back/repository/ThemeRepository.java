package edu.esiea.quiz_back.repository;

import edu.esiea.quiz_back.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}
