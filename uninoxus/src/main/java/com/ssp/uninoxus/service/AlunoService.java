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
	
	public Aluno adiconar (Aluno aluno) {
		
		return alunoRepository.save(aluno);
	} 
 
	public void  update (Aluno aluno) {
		
		 Aluno alunoExistente = this.alunoRepository  
		             .findById(aluno.getMatriculaAluno())
		            .orElseThrow(() -> new  IllegalArgumentException("Avaliação não encontrada!"));
		 
		 alunoExistente.setCurso(aluno.getCurso());
		 alunoExistente.setMatricula(aluno.getMatricula());
		 
		 this.alunoRepository.save(alunoExistente);} 
}
