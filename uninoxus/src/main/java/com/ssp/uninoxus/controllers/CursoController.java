package com.ssp.uninoxus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.CriarCursoDTO;
import com.ssp.uninoxus.dto.CursoResponseDTO;
import com.ssp.uninoxus.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cursos")
@CrossOrigin(origins = "*")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@PostMapping 
    public ResponseEntity<CursoResponseDTO> insert (@RequestBody @Valid CriarCursoDTO dto){ 
		
		 return ResponseEntity.status(201).body(cursoService.adicionar(dto)); }  
	 
		 
}
