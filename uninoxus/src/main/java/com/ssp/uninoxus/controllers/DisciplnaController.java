package com.ssp.uninoxus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.service.DisciplinaService;

@RestController
@RequestMapping (value = "/disciplina")
public class DisciplnaController {

	@Autowired
	private DisciplinaService disciplinaService;
	
	@PostMapping 
    public ResponseEntity<Disciplina> insert (@RequestBody Disciplina disciplina){ 
		
		disciplina = disciplinaService.adicionar(disciplina);  
		 return ResponseEntity.status(201).body(disciplina); }
}
