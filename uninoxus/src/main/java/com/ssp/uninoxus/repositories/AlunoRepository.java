package com.ssp.uninoxus.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.enums.TipoAvaliacao;


@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long>{

	Optional<Avaliacao> findByMatricula_IdMatriculaAndTipoAvaliacao(Long idMatricula, TipoAvaliacao tipo);
	 
	
}