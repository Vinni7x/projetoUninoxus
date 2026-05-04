package com.ssp.uninoxus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.service.AvaliacaoService;

@RestController
@RequestMapping (value = "avalaicao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	
	@PostMapping 
    public ResponseEntity<Avaliacao> insert (@RequestBody Avaliacao avaliacao){ 
		
		 avaliacao = avaliacaoService.adicionar(avaliacao);  
		 return ResponseEntity.status(201).body(avaliacao); }
	
	@PutMapping("/lanca-nota")
	public ResponseEntity<?> lancaNota(@RequestBody Avaliacao avaliacao) throws  IllegalArgumentException {
	    avaliacaoService.lancaNota(avaliacao);
	    return ResponseEntity.ok().build(); 
	} 

}
