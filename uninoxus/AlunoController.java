package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll(){
		List<Aluno> lista = alunoService.findAll(); 
		return ResponseEntity.ok(lista);     
	} 	
 
}
