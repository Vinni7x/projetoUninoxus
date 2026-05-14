package com.ssp.uninoxus.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.enums.TipoAvaliacao;

import jakarta.validation.constraints.NotNull;


@Repository
public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long>{

    // select * from avaliacao where idmatricula = ?;
    /*~~(class org.openrewrite.java.tree.J$Erroneous cannot be cast to class org.openrewrite.java.tree.J$Assignment (org.openrewrite.java.tree.J$Erroneous and org.openrewrite.java.tree.J$Assignment are in unnamed module of loader 'app'))~~>*/@Query("SELECT a FROM Avaliacao a WHERE a.matricula.idMatricula = :idMatricula")
    List<Avaliacao> findAllByMatricula_IdMatricula(Long idMatricula);

	Optional<Avaliacao> findByMatricula_IdMatriculaAndTipoAvaliacao(Long idMatricula, TipoAvaliacao tipoAvaliacao);

	boolean existsByMatricula_IdMatriculaAndTipoAvaliacao(@NotNull Long idMatricula,
			@NotNull TipoAvaliacao tipoAvaliacao);

}
 
 