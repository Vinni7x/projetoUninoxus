package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.CriarTurmaDTO;

import com.ssp.uninoxus.dto.TurmaMatriculadoDTO;
import com.ssp.uninoxus.dto.TurmaMinistradaDTO;
import com.ssp.uninoxus.dto.TurmaResponseDTO;
import com.ssp.uninoxus.service.TurmaService;

import jakarta.validation.Valid;

@RequestMapping("/turmas")
@RestController

public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	
	@PostMapping 
    public ResponseEntity<TurmaResponseDTO> Adicionar (@RequestBody @Valid CriarTurmaDTO dto){ 
		
		 return ResponseEntity.status(201).body(turmaService.adicionar(dto)); } 
	
	  
	@GetMapping("/{matriculaAluno}/turmasmatricula")
	public ResponseEntity<List<TurmaMatriculadoDTO>> turmasMatriculado(@PathVariable Long matriculaAluno){
		List<TurmaMatriculadoDTO> lista = turmaService.turmasMatriculado(matriculaAluno);
		return ResponseEntity.ok(lista); 
		  
	}
	
	@GetMapping("/{idCurso}/turmasabertas/{matriculaAluno}") 
	public ResponseEntity<List<TurmaResponseDTO>> turmasAbertas(@PathVariable Long idCurso, @PathVariable Long matriculaAluno ){
		List<TurmaResponseDTO> lista = turmaService.verTurmasAbertas(idCurso, matriculaAluno);
		return ResponseEntity.ok(lista); }  
	 
	@PostMapping("/{idTurma}/consolidar")
	public ResponseEntity<?> consolidar(@PathVariable Long idTurma) { 
	    try {
	        turmaService.consolidar(idTurma);
	        return ResponseEntity.ok().build(); 
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage()); 
	    }
	} 
	
	@PutMapping ("/{idTurma}")
	public ResponseEntity<TurmaResponseDTO> update (@RequestBody @Valid CriarTurmaDTO dto,@PathVariable Long idTurma){
		return ResponseEntity.status(201).body(turmaService.update(dto, idTurma));
	}
	
	
	@GetMapping
	public ResponseEntity<List<TurmaResponseDTO>> todosTurmas (){ 
		
		List<TurmaResponseDTO> lista = turmaService.listarTodasTurmas();
		
		return ResponseEntity.ok(lista);
		
	}
	
	@GetMapping("/{matriculaProfessor}/turmasministradas")
	public ResponseEntity<List<TurmaMinistradaDTO>> turmasMinistrada(@PathVariable Long matriculaProfessor){
		List<TurmaMinistradaDTO> lista = turmaService.turmasMinistradas(matriculaProfessor);
		return ResponseEntity.ok(lista); 
		  
	}
	
	@GetMapping("/{idCurso}/turmascursos")
	public ResponseEntity<List<TurmaResponseDTO>> turmasCurso(@PathVariable Long idCurso){
		List<TurmaResponseDTO> lista = turmaService.listarPorCurso(idCurso);
		return ResponseEntity.ok(lista); }   
	
	@GetMapping("/{idTurma}") 
	public ResponseEntity<TurmaResponseDTO> buscarporID (@PathVariable Long idTurma){
		return ResponseEntity.status(200).body(turmaService.listarPorId(idTurma));  
		
	}
}
 