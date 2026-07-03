package com.ssp.uninoxus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.CriarCursoDTO;
import com.ssp.uninoxus.dto.CursoResponseDTO;
import com.ssp.uninoxus.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cursos")

public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@PostMapping 
    public ResponseEntity<CursoResponseDTO> insert (@RequestBody @Valid CriarCursoDTO dto){ 
		
		 return ResponseEntity.status(201).body(cursoService.adicionar(dto)); }  
	
	@PutMapping("/{idCurso}")
	public ResponseEntity<CursoResponseDTO> update( @RequestBody @Valid CriarCursoDTO dto, @PathVariable Long idCurso) {
	    return ResponseEntity.ok(cursoService.update(dto, idCurso));  
	}
		 
	@GetMapping
	public ResponseEntity<Page<CursoResponseDTO>> todosCursos (@RequestParam int pagina, @RequestParam int itens){
		 
		Page<CursoResponseDTO> lista = cursoService.listarTodosCursos(pagina, itens);
		 
		return ResponseEntity.ok(lista);
		
	}
	
	@GetMapping("/{idCurso}") 
	public ResponseEntity<CursoResponseDTO> buscarporID (@PathVariable Long idCurso){
		return ResponseEntity.status(200).body(cursoService.listarPorId(idCurso));  
		
	}
}  
