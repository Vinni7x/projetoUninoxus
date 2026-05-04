package com.ssp.uninoxus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository <Disciplina, Long> {
 
}
  