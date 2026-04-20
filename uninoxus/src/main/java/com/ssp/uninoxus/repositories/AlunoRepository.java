package com.ssp.uninoxus.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long>{
	 
	
}