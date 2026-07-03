package com.ssp.uninoxus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarCursoDTO;
import com.ssp.uninoxus.dto.CursoResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	public Page<CursoResponseDTO> listarTodosCursos(int pagina, int itens) {
		PageRequest pageRequest = PageRequest.of(pagina, itens);
	    Page<Curso> cursos = cursoRepository.findAll(pageRequest);
	    List<CursoResponseDTO> lista = new ArrayList<>();
	    for (Curso c : cursos) { 
	        lista.add(toDTO(c));
	    }
	    return new PageImpl<>(lista, pageRequest, cursos.getTotalElements());
	}
	
	public CursoResponseDTO adicionar (CriarCursoDTO dto) {
		   boolean cursoJaExiste = cursoRepository.existsByNomeCursoIgnoreCase(dto.nomeCurso());
		   if (cursoJaExiste) {
			    throw new IllegalArgumentException("Esse curso já existe!");
			} 
	
		 Curso curso = new Curso(); 
	        curso.setNomeCurso(dto.nomeCurso());
	        curso.setCargaHorariaTotal(dto.cargaHorariaTotal());
	      
	        
	        cursoRepository.save(curso);
	        return toDTO(curso);  
		  
		}
	 
	 public CursoResponseDTO update (CriarCursoDTO dto, Long idCurso) {
		  
		   Curso cursoExistente = cursoRepository.findById(idCurso)
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrada!"));
		   
		   if (!cursoExistente.getNomeCurso().equalsIgnoreCase(dto.nomeCurso())) {
			   boolean nomeJaExiste = cursoRepository.existsByNomeCursoIgnoreCase(dto.nomeCurso());
		        if (nomeJaExiste) {
		            throw new IllegalArgumentException("Já existe outro curso cadastrado com este nome!");
		        }
		   }
		   
		   cursoExistente.setNomeCurso(dto.nomeCurso());
		   cursoExistente.setCargaHorariaTotal(dto.cargaHorariaTotal()); 

	        cursoRepository.save(cursoExistente); 
	        return toDTO(cursoExistente);	  
	 }
	 
	 
	    public void deletar(Long idCurso) {
	        if (!cursoRepository.existsById(idCurso)) {
	            throw new IllegalArgumentException("Curso não encontrada, impossível apagar!"); 
	        }
	        cursoRepository.deleteById(idCurso); 
	    }
	    
	    public CursoResponseDTO listarPorId(Long idCurso){
	    	Curso curso = cursoRepository.findById(idCurso)
	    			.orElseThrow(() -> new IllegalArgumentException("Curso " + idCurso + " não encontrado!"));
	        ;
	    	return toDTO(curso); 
	    } 
	
	    
	    
	 private CursoResponseDTO toDTO(Curso curso) {
	        return new CursoResponseDTO(
	            curso.getIdCurso(),
	            curso.getNomeCurso(),
	            curso.getCargaHorariaTotal()
	           
	        );
	    }
	 
	
	} 


