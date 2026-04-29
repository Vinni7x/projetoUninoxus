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

}
