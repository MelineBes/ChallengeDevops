package edu.esiea.quiz_back.controller;

import edu.esiea.quiz_back.entity.Theme;
import edu.esiea.quiz_back.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/themes")
@CrossOrigin
public class ThemeController {

    private final ThemeService service;

    @Autowired
    public ThemeController(ThemeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Theme> getAllThemes() {
        return service.getAllThemes();
    }

    @GetMapping("/{id}")
    public Optional<Theme> getThemeById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Theme createTheme(@RequestBody Theme theme) {
        return service.createTheme(theme);
    }

    @PutMapping("/{id}")
    public Theme updateTheme(@PathVariable int id, @RequestBody Theme theme) {
        return service.updateTheme(id, theme);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTheme(@PathVariable int id) {
        return service.deleteTheme(id);
    }
}
