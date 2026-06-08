package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<ProfessorResponseDTO>> todosCursos (){
		
		List<ProfessorResponseDTO> lista = professorService.listarTodosProfessores();
		
		return ResponseEntity.ok(lista);
		
	}
} 
 