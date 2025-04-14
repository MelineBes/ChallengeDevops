package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Theme;
import edu.esiea.quiz_back.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    private final ThemeRepository repository;

    @Autowired
    public ThemeService(ThemeRepository repository) {
        this.repository = repository;
    }

    public List<Theme> getAllThemes() {
        return repository.findAll();
    }

    public Optional<Theme> getById(int id) {
        return repository.findById(id);
    }

    public Theme createTheme(Theme theme) {
        return repository.save(theme);
    }

    public Theme updateTheme(int id, Theme themeDetails) {
        Optional<Theme> existing = repository.findById(id);

        if (existing.isPresent()) {
            Theme theme = existing.get();
            theme.setName(themeDetails.getName());
            theme.setQuizz(themeDetails.getQuizz());
            return repository.save(theme);
        } else {
            return null;
        }
    }

    public boolean deleteTheme(int id) {
        Optional<Theme> theme = repository.findById(id);
        if (theme.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
