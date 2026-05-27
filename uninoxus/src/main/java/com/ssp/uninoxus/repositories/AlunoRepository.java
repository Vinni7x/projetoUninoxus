package com.ssp.uninoxus.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Aluno;



@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long>{

	boolean existsByCpf(String cpf);

	List<Aluno> findByMatriculas_Turma_IdTurma(Long idTurma);

	//Optional<Avaliacao> findByMatricula_IdMatriculaAndTipoAvaliacao(Long idMatricula, TipoAvaliacao tipo);
	 
	
}