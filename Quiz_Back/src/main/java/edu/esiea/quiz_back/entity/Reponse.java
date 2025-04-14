package edu.esiea.quiz_back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Reponse")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texte;
    private boolean correcte;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
