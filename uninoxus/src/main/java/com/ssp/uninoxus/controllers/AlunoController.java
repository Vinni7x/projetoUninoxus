package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.AlunoResponseDTO;
import com.ssp.uninoxus.dto.AlunoTurmaDTO;
import com.ssp.uninoxus.dto.CriarAlunoDTO;
import com.ssp.uninoxus.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	
	
	@GetMapping("{idTurma}/alunosturma")
	public ResponseEntity<List<AlunoTurmaDTO>> TodosAlunosTurma(@PathVariable Long idTurma){
		List<AlunoTurmaDTO> lista = alunoService.findAllByTurma(idTurma); 
		return ResponseEntity.ok(lista);}  
	
	@GetMapping("{idTurma}/solicitacaoalunos")
	public ResponseEntity<List<AlunoTurmaDTO>> TodosAlunosSolicitados(@PathVariable Long idTurma){
		List<AlunoTurmaDTO> lista = alunoService.findAllByMatriculaSolicitada(idTurma); 
		return ResponseEntity.ok(lista);}  
 
	@PostMapping 
    public ResponseEntity<AlunoResponseDTO> insert (@RequestBody @Valid CriarAlunoDTO dto){ 
		
		return ResponseEntity.status(201).body(alunoService.adicionar(dto)); }
	
	@PutMapping ("/{matriculaAluno}")
	public ResponseEntity<AlunoResponseDTO> update (
	    @RequestBody @Valid  CriarAlunoDTO  dto,  @PathVariable Long matriculaAluno){
		
		 return ResponseEntity.ok(alunoService.update(dto, matriculaAluno));    
	}
	
	@GetMapping("/{matriculaAluno}") 
	public ResponseEntity<AlunoResponseDTO> buscarporID (@PathVariable Long matriculaAluno){
		return ResponseEntity.status(200).body(alunoService.buscarPorId(matriculaAluno));  
		
	}
}  