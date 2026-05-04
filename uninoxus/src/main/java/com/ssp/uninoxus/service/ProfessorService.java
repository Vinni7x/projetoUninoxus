package com.ssp.uninoxus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	
	@Autowired
	private ProfessorRepository professorRepository;

	public List <Professor> findAll(){
		return professorRepository.findAll();
	}
}
