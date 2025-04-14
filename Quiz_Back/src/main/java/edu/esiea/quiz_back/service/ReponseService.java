package edu.esiea.quiz_back.service;

import edu.esiea.quiz_back.entity.Reponse;
import edu.esiea.quiz_back.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseService {

    private final ReponseRepository repository;

    @Autowired
    public ReponseService(ReponseRepository repository) {
        this.repository = repository;
    }

    public List<Reponse> getAllReponses() {
        return repository.findAll();
    }

    public Optional<Reponse> getReponseById(int id) {
        return repository.findById(id);
    }

    public Reponse createReponse(Reponse reponse) {
        return repository.save(reponse);
    }

    public Reponse updateReponse(int id, Reponse reponseDetails) {
        Optional<Reponse> existing = repository.findById(id);

        if (existing.isPresent()) {
            Reponse reponse = existing.get();
            reponse.setTexte(reponseDetails.getTexte());
            reponse.setCorrecte(reponseDetails.isCorrecte());
            reponse.setQuestion(reponseDetails.getQuestion());
            return repository.save(reponse);
        } else {
            return null;
        }
    }

    public boolean deleteReponse(int id) {
        Optional<Reponse> existing = repository.findById(id);
        if (existing.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
