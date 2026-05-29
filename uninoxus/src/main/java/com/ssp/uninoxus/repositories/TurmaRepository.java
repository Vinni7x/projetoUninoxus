package com.ssp.uninoxus.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.StatusTurma;

@Repository
public interface TurmaRepository extends JpaRepository <Turma, Long>{
	 
	List<Turma> findByCursoIdCursoAndStatusTurma(Long idCurso, StatusTurma statusTurma);

	List<Turma> findByProfessorMatriculaProfessor(Long matriculaProfessor);
}    