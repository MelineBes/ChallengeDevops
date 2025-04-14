package edu.esiea.quiz_back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reponse_utilisateur")
public class ReponseUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "reponse_id")
    private Reponse reponse;

    private boolean correcte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public boolean isCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }

    public ReponseUtilisateur() {

    }

    public ReponseUtilisateur(Utilisateur utilisateur, Question question, Reponse reponse, boolean correcte) {
        this.utilisateur = utilisateur;
        this.question = question;
        this.reponse = reponse;
        this.correcte = correcte;
    }
}
