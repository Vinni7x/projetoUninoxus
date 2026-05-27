package com.ssp.uninoxus.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.AvaliacaoResponseDTO;
import com.ssp.uninoxus.dto.CriarAvaliacaoDTO;
import com.ssp.uninoxus.service.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping (value = "/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {
	
	@Autowired 
	private AvaliacaoService avaliacaoService; 
	
    @GetMapping("/{matriculaAluno}/provas")
	public  ResponseEntity<List<AvaliacaoResponseDTO>> todasProvas(@PathVariable Long matriculaAluno){
		List<AvaliacaoResponseDTO> lista = avaliacaoService.avaliacoesDoAluno(matriculaAluno); 
		return ResponseEntity.status(200).body(lista);  
		 
	}
	
	@PostMapping 
    public ResponseEntity<AvaliacaoResponseDTO> insert (@RequestBody @Valid CriarAvaliacaoDTO dto){ 
		
		 return ResponseEntity.status(201).body(avaliacaoService.adicionar(dto)); } 
	
	@PutMapping ("/{idAvaliacao}")
	public ResponseEntity<AvaliacaoResponseDTO> update ( @PathVariable Long idAvaliacao,
	    @RequestBody @Valid  CriarAvaliacaoDTO dto){
		
		 return ResponseEntity.ok(avaliacaoService.update(idAvaliacao, dto));
	}
	
	
	@DeleteMapping( "/{idAvaliacao}")  
	public  ResponseEntity <Void> deletar (@PathVariable Long idAvaliacao ){
		try {
			avaliacaoService.deletar(idAvaliacao);
			return ResponseEntity.noContent().build(); }
			catch (RuntimeException e) {
				return ResponseEntity.notFound().build();
			}
	}
	
	  @GetMapping("/{matriculaProfessor}/provasProfessor")
		public  ResponseEntity<List<AvaliacaoResponseDTO>> todasProvasProfessor(@PathVariable Long matriculaProfessor){
			List<AvaliacaoResponseDTO> lista = avaliacaoService.avaliacoesDoProfessor(matriculaProfessor); 
			return ResponseEntity.status(200).body(lista);  
			 
		} 
	  
	  @GetMapping("/{idTurma}/provasTurma")
		public  ResponseEntity<List<AvaliacaoResponseDTO>>avaliacoesDaTurma (@PathVariable Long idTurma){
			List<AvaliacaoResponseDTO> lista = avaliacaoService.avaliacoesDaTurma(idTurma); 
			return ResponseEntity.status(200).body(lista);  
			    
		}
	  
	  @PatchMapping("/{idAvaliacao}/finalizar")
	  public ResponseEntity <Void> finalizarProvas(Long idAvaliacao){
			try {
				avaliacaoService.finalizar(idAvaliacao);
				return ResponseEntity.noContent().build(); }
				catch (RuntimeException e) {
					return ResponseEntity.notFound().build();
				} 
	  }
	  
	  
	  
} 
  