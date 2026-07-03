package com.ssp.uninoxus.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Professor;



@Repository
public interface ProfessorRepository extends JpaRepository <Professor, Long>{

	boolean existsByCpf(String cpf);

	List<Professor> findByCurso_IdCurso(Long idCurso);
	  
	Page<Professor> findAll(Pageable pageable); 
	
}