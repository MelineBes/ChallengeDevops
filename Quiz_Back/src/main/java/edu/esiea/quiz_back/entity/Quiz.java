package edu.esiea.quiz_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz() {

    }

    public Quiz(String titre, Theme theme, List<Question> questions) {
        this.titre = titre;
        this.theme = theme;
        this.questions = questions;
    }
}
