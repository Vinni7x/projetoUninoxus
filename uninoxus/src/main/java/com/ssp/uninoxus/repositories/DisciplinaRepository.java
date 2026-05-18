package com.ssp.uninoxus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Disciplina;

import jakarta.validation.constraints.NotBlank;

@Repository
public interface DisciplinaRepository extends JpaRepository <Disciplina, Long> {

	boolean existsByNomeDisciplinaIgnoreCase(String nomeDisciplina);

	boolean existsByNomeCursoIgnoreCase(
			@NotBlank(message = "nome da disciplina  não pode ser vazia") String nomeDisciplina);
 
}
   