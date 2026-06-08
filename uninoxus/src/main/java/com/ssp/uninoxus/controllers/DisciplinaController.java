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

import com.ssp.uninoxus.dto.CriarDisciplinaDTO;
import com.ssp.uninoxus.dto.DisciplinaResponseDTO;

import com.ssp.uninoxus.service.DisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping (value = "/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	
	@PostMapping 
    public ResponseEntity<DisciplinaResponseDTO> insert (@RequestBody @Valid CriarDisciplinaDTO dto){ 
		
		 return ResponseEntity.status(201).body(disciplinaService.adicionar(dto)); } 
	
	@GetMapping("/{idCurso}/disciplinacurso")
	public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinaCurso(@PathVariable Long idCurso){
		List<DisciplinaResponseDTO> lista = disciplinaService.listarPorCurso(idCurso);
		return ResponseEntity.ok(lista);}  
	
	@GetMapping
	public ResponseEntity<List<DisciplinaResponseDTO>> listarTodasDiscipnas(){
		List<DisciplinaResponseDTO> lista = disciplinaService.listarTodasDisciplina();
		return ResponseEntity.ok(lista); 
	}
	
	@GetMapping("/{idDisciplina}") 
	public ResponseEntity<DisciplinaResponseDTO> buscarporID (@PathVariable Long idDisciplina){
		return ResponseEntity.status(200).body(disciplinaService.listarPorId(idDisciplina));  
		
	}
}
  