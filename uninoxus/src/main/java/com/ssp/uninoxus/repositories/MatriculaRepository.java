package com.ssp.uninoxus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.uninoxus.entities.Matricula;


@Repository
public interface MatriculaRepository extends JpaRepository <Matricula, Long>{
	 
	
}