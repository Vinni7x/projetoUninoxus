package com.ssp.uninoxus.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.entities.Matricula;

@Repository
public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long>{


	List<Avaliacao> findAllByMatricula_IdMatricula(Long idMatricula);

}
  