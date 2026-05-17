package com.ssp.uninoxus.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssp.uninoxus.entities.Professor;



@Repository
public interface ProfessorRepository extends JpaRepository <Professor, Long>{

	boolean existsByCpf(String cpf);
	 
	
}