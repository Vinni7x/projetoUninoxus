package com.ssp.uninoxus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public List <Curso> findAll(){
		return cursoRepository.findAll();
	}
	
	public Optional<Curso> findById(Long idCurso){
		if (idCurso != null) {
			return cursoRepository.findById(idCurso);
		}
			return Optional.empty(); 
	}
	
	//analisar qual usar dps runTime ou Illegal
	public Curso adicionar (Curso curso) {
		
		if(curso.getNomeCurso() == null || curso.getNomeCurso().isBlank()) {
			throw new IllegalArgumentException ("O nome do curso não pode ser vazio");
		}
		if (cursoRepository.existsByNomeCursoIgnoreCase(curso.getNomeCurso())) { 
			throw new RuntimeException ("Não foi possível adicionar: Curso com este nome já existe!");}
		
		return cursoRepository.save(curso); 
		}
	} 


