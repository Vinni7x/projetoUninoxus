package com.ssp.uninoxus.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Aluno;



@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long>{

	boolean existsByCpf(String cpf);

	Page<Aluno> findByMatriculas_Turma_IdTurma(Long idTurma, Pageable pageable); 
	
	Page<Aluno> findAll(Pageable pegleable);
	
	List<Aluno> findByMatriculas_Turma_IdTurma(Long idTurma); 
	
}  