package com.ssp.uninoxus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.uninoxus.entities.Nota;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.TipoAvaliacao;


public interface NotaRepository extends JpaRepository <Nota, Long> {

	Optional<Turma> findAllByMatricula_IdMatricula(Long idMatricula);

	Optional<Nota> findByAvaliacao_IdAvaliacaoAndMatricula_IdMatricula(Long idAvaliacao, Long idMatricula);

	Optional<Nota> findByMatricula_IdMatriculaAndAvaliacao_TipoAvaliacao(Long idMatricula, TipoAvaliacao tipoAvaliacao); 

}   
 