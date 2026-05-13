package com.ssp.uninoxus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Matricula;


@Repository
public interface MatriculaRepository extends JpaRepository <Matricula, Long>{

	boolean existsByAluno_MatriculaAlunoAndTurma_IdTurma(Long matriculaAluno, Long idTurma);

	  List<Matricula> findAllByAluno_MatriculaAluno(Long matriculaAluno);
	 
	
}   