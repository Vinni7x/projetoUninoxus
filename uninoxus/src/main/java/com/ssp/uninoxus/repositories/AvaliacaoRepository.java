package com.ssp.uninoxus.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long>{

}
