package edu.esiea.quiz_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    // Relations
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<ReponseUtilisateur> reponses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ReponseUtilisateur> getReponses() {
        return reponses;
    }

    public void setReponses(List<ReponseUtilisateur> reponses) {
        this.reponses = reponses;
    }

    public Utilisateur() {

    }

    public Utilisateur(String nom, List<ReponseUtilisateur> reponses) {
        this.nom = nom;
        this.reponses = reponses;
    }
}
