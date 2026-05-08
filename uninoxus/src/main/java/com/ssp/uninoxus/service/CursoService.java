package com.ssp.uninoxus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarCursoDTO;
import com.ssp.uninoxus.dto.CursoResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	public CursoResponseDTO adicionar (CriarCursoDTO dto) {
		
		 Curso curso = new Curso();
	        curso.setNomeCurso(dto.nomeCurso());
	        curso.setCargaHorariaTotal(dto.cargaHorariaTotal());
	      
	       
	        cursoRepository.save(curso);
	        return toDTO(curso);  
		  
		}
	 
	 public CursoResponseDTO update (CriarCursoDTO dto, Long idCurso) {
		  
		   Curso cursoExistente = cursoRepository.findById(idCurso)
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrada!"));
		   
		   cursoExistente.setNomeCurso(dto.nomeCurso());
		   cursoExistente.setCargaHorariaTotal(dto.cargaHorariaTotal()); 

	        cursoRepository.save(cursoExistente); 
	        return toDTO(cursoExistente);
		  
		 
	 }
	
	 private CursoResponseDTO toDTO(Curso curso) {
	        return new CursoResponseDTO(
	            curso.getIdCurso(),
	            curso.getNomeCurso(),
	            curso.getCargaHorariaTotal()
	           
	        );
	    }
	 
	
	} 


