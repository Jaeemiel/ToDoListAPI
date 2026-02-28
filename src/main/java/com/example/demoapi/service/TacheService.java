package com.example.demoapi.service;

import com.example.demoapi.entity.Tache;
import com.example.demoapi.repository.TacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public List<Tache> findAll(){
        return tacheRepository.findAll();
    }

    public Optional<Tache> findById(Long id) {
        return tacheRepository.findById(id);
    }

    public Tache create(Tache tache) {
        return tacheRepository.save(tache);
    }

    public Tache update(Long id, Tache tache) {
        tache.setId(id);
        return tacheRepository.save(tache);
    }

    public void delete(Long id) {
        tacheRepository.deleteById(id);
    }
}
