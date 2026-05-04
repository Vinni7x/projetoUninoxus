package com.ssp.uninoxus.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ssp.uninoxus.entities.Curso;

import com.ssp.uninoxus.service.CursoService;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	
	@GetMapping
	public ResponseEntity<List<Curso>> findAll(){
		List<Curso> lista = cursoService.findAll(); 
		return ResponseEntity.ok(lista); 	
	}
	 
	@GetMapping (value = "/idCurso")
	public ResponseEntity<Optional<Curso>> findById(Long idCurso){
		Optional<Curso> lista = cursoService.findById(idCurso); 
		return ResponseEntity.ok(lista); 	
	}
	
	@PostMapping 
	public ResponseEntity<Curso> insert (@RequestBody Curso curso){ 
		
		 curso = cursoService.adicionar(curso);  
		 return ResponseEntity.status(201).body(curso); 
		   
	} 

}
