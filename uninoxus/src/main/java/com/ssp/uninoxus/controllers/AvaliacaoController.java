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
import com.ssp.uninoxus.dto.CriarAvaliacaoDTO;
import com.ssp.uninoxus.dto.LancarNotaDTO;
import com.ssp.uninoxus.service.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping (value = "/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping("/{idMatricula}")
	public  ResponseEntity<List<AvaliacaoResponseDTO>> todasProvas(@PathVariable Long idMatricula){
		List<AvaliacaoResponseDTO> lista = avaliacaoService.todasProvas(idMatricula);
		return ResponseEntity.status(200).body(lista); 
		 
	}
	
	@PostMapping 
    public ResponseEntity<AvaliacaoResponseDTO> insert (@RequestBody @Valid CriarAvaliacaoDTO dto){ 
		
		 return ResponseEntity.status(201).body(avaliacaoService.adicionar(dto)); } 
	
	@PatchMapping("/{idAvaliacao}/nota")
	public ResponseEntity<AvaliacaoResponseDTO> lancaNota(
	    @PathVariable Long idAvaliacao,
	    @RequestBody @Valid LancarNotaDTO dto) {
	    
	    return ResponseEntity.ok(avaliacaoService.lancaNota(idAvaliacao, dto));
	}
} 
  