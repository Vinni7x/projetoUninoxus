package com.ssp.uninoxus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.repositories.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List <Aluno> findAll(){
		return alunoRepository.findAll(); 
	}
	

}
