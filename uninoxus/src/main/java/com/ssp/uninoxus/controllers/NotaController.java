package com.ssp.uninoxus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.LancarNotaDTO;
import com.ssp.uninoxus.dto.NotaResponseDTO;
import com.ssp.uninoxus.service.NotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notas")

public class NotaController {
	
	@Autowired
	private NotaService notaService;
	
	@PostMapping 
	public ResponseEntity<NotaResponseDTO> lancarNota(
	    @RequestBody @Valid LancarNotaDTO dto) { 	
	     
	    return ResponseEntity.ok(notaService.lancarNota(dto)); 
	}
	
	@GetMapping("/{idAvaliacao}")
	public ResponseEntity <NotaResponseDTO> notaPorAvaliacao(@PathVariable Long idAvaliacao){
		return ResponseEntity.status(200).body(notaService.notaPorAvaliacao(idAvaliacao));}
	
	  	
	@GetMapping("/{idAvaliacao}/{idMatricula}")
	public ResponseEntity<NotaResponseDTO> notaPorAvaliacaoEMatricula(@PathVariable Long idAvaliacao,
        @PathVariable Long idMatricula) {

    NotaResponseDTO nota = notaService.notaPorAvaliacaoEMatricula(idAvaliacao, idMatricula);

    if (nota == null) return ResponseEntity.noContent().build(); 
    return ResponseEntity.ok(nota);}
}