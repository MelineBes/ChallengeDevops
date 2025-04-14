package edu.esiea.quiz_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "theme")
    private List<Quiz> quizz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizz() {
        return quizz;
    }

    public void setQuizz(List<Quiz> quizz) {
        this.quizz = quizz;
    }

    public Theme() {

    }

    public Theme(String name, List<Quiz> quizz) {
        this.name = name;
        this.quizz = quizz;
    }
}
