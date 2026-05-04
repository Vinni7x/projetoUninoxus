package com.ssp.uninoxus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.repositories.DisciplinaRepository;


@Service
public class DisciplinaService {

		@Autowired
		private DisciplinaRepository disciplinaRepository; 
		
		
		public List<Disciplina> listar() {
			return disciplinaRepository.findAll();
		}
			
		public Disciplina adicionar (Disciplina disciplina) {
			
			if(disciplina.getNomeDisciplina() == null || disciplina.getNomeDisciplina().isBlank()) {
				throw new IllegalArgumentException ("O nome do curso não pode ser vazio");
			}
			if (disciplinaRepository.existsByNomeDisciplinaIgnoreCase(disciplina.getNomeDisciplina())) { 
				throw new RuntimeException ("Não foi possível adicionar: Curso com este nome já existe!");}
			
			return disciplinaRepository.save(disciplina);  
			}
		 
		
	 }

