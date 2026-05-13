package com.ssp.uninoxus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.CriarTurmaDTO;
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
	
	  
	   
}
 