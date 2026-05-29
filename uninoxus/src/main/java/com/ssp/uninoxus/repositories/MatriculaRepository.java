package com.ssp.uninoxus.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ssp.uninoxus.entities.Matricula;

import com.ssp.uninoxus.enums.StatusMatricula;


@Repository
public interface MatriculaRepository extends JpaRepository <Matricula, Long>{

	boolean existsByAluno_MatriculaAlunoAndTurma_IdTurma(Long matriculaAluno, Long long1);

	  List<Matricula> findAllByAluno_MatriculaAluno(Long matriculaAluno);

	  List<Matricula> findByAluno_MatriculaAlunoAndStatusMatricula(Long matriculaAluno, StatusMatricula matriculado);

	  List<Matricula> findByAluno_MatriculaAlunoAndStatusMatriculaIn(Long matriculaAluno,
			List<StatusMatricula> statusAlvo);

	 
 
	  

	   
}      