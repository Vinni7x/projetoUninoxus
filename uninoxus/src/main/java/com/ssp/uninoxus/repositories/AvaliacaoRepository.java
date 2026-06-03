package com.ssp.uninoxus.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.TipoAvaliacao;



@Repository
public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long>{

  
    List<Avaliacao> findAllByTurma_IdTurma(Long idTurma); 

	Optional<Avaliacao> findByTurma_IdTurmaAndTipoAvaliacao(Long idTurma, TipoAvaliacao tipoAvaliacao);

	boolean existsByTurma_IdTurmaAndTipoAvaliacao(Long idTurma, TipoAvaliacao tipoAvaliacao);
 
	List<Avaliacao> findByTurma_Matriculas_Aluno_MatriculaAluno(Long matriculaAluno);
	
	List<Avaliacao> findByTurma_Matriculas_Aluno_MatriculaAlunoAndTurma_Matriculas_StatusMatricula(Long matriculaAluno, StatusMatricula statusMatricula);

	List<Avaliacao> findByTurma_Professor_MatriculaProfessorAndTurma_StatusTurma(Long matriculaProfessor, StatusTurma statusTurma);

	List<Avaliacao> findByTurma_IdTurma(Long idTurma); 

}      
  
  