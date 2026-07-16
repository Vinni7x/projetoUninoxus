package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.CriarProfessorDTO;
import com.ssp.uninoxus.dto.ProfessorResponseDTO;
import com.ssp.uninoxus.service.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professores")

public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping 
    public ResponseEntity<ProfessorResponseDTO> insert (@RequestBody @Valid CriarProfessorDTO dto){ 
		
		 return ResponseEntity.status(201).body(professorService.adicionar(dto)); } 
	
	

	@GetMapping("/{matriculaProfessor}") 
	public ResponseEntity<ProfessorResponseDTO> buscarporID (@PathVariable Long matriculaProfessor){
		return ResponseEntity.status(200).body(professorService.buscarPorId(matriculaProfessor));  
	} 
	
	@GetMapping("/{idCurso}/professorcurso")
	public ResponseEntity<List<ProfessorResponseDTO>> buscarProfessorCurso(@PathVariable Long idCurso){
		List<ProfessorResponseDTO> lista = professorService.listarProfessorporCurso(idCurso); 
		return ResponseEntity.ok(lista);	
	}
	
	@GetMapping
	public ResponseEntity<Page<ProfessorResponseDTO>> todosProfessores (@RequestParam int pagina, @RequestParam int itens){
		
		Page<ProfessorResponseDTO> lista = professorService.listarTodosProfessores(pagina, itens);
		
		return ResponseEntity.ok(lista); 
		 
	}
	
	@DeleteMapping( "/{matriculaProfessor}")  
	public  ResponseEntity <Void> deletar (@PathVariable Long matriculaProfessor){
		try {
			professorService.deletar(matriculaProfessor);
			return ResponseEntity.noContent().build(); }
			catch (RuntimeException e) {
				return ResponseEntity.notFound().build();
			}
	}
} 
 