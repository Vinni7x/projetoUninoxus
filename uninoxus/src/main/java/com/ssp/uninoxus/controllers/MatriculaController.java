package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.AvaliacaoResponseDTO;
import com.ssp.uninoxus.dto.CriarMatriculaDTO;
import com.ssp.uninoxus.dto.MatriculaResponseDTO;
import com.ssp.uninoxus.service.MatriculaService;

import jakarta.validation.Valid;

@RequestMapping ("/matriculas")
@RestController
public class MatriculaController {
	
	
	@Autowired
	private MatriculaService matriculaService;
	
	
	@GetMapping("/{matriculaAluno}")
	public ResponseEntity<List<MatriculaResponseDTO>> todasMatriculas (@PathVariable Long matriculaAluno){
		List<MatriculaResponseDTO> lista = matriculaService.todasMatriculas(matriculaAluno);
		return ResponseEntity.status(200).body(lista);   
		
	}

	
	@PostMapping
	public ResponseEntity<MatriculaResponseDTO> adicionar (@RequestBody @Valid CriarMatriculaDTO dto){
		return ResponseEntity.status(201).body(matriculaService.adicionar(dto));
	}
	 
	@PatchMapping("/{idMatricula}/cancelar")
	public ResponseEntity<Void> cancelar(@PathVariable Long idMatricula) {
	    matriculaService.cancelar(idMatricula);
	    return ResponseEntity.noContent().build();
	}
    
}
