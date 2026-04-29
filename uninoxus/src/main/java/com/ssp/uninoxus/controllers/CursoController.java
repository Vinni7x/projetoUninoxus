package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.service.CursoService;
import com.ssp.uninoxus.entities.Curso;

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
	

}
