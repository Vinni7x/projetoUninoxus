package com.ssp.uninoxus.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Curso;






@Repository
public interface CursoRepository extends JpaRepository <Curso, Long>{

	boolean existsByNomeCursoIgnoreCase(String nomeCurso);

	Page<Curso> findAll(Pageable pegleable);
	
}
  