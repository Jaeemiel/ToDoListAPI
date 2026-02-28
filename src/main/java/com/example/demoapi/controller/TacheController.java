package com.example.demoapi.controller;

import com.example.demoapi.entity.Tache;
import com.example.demoapi.service.TacheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping
    public ResponseEntity<List<Tache>> findAll() {
        return ResponseEntity.ok(tacheService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tache> findById(@PathVariable Long id) {
        return tacheService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tache> create(@RequestBody Tache tache) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tacheService.create(tache));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> update(@PathVariable Long id, @RequestBody Tache tache) {
        return tacheService.findById(id)
                .map(t -> ResponseEntity.ok(tacheService.update(id, tache)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return tacheService.findById(id)
                .map(t -> {
                    tacheService.delete(id);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}