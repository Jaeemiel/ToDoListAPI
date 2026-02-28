package com.example.demoapi.repository;

import com.example.demoapi.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {

}
