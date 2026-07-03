package com.ssp.uninoxus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssp.uninoxus.dto.AlunoResponseDTO;
import com.ssp.uninoxus.dto.AlunoTurmaDTO;
import com.ssp.uninoxus.dto.CriarAlunoDTO;
import com.ssp.uninoxus.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService; 
	
	
	
	@GetMapping("{idTurma}/alunosturma")
	public ResponseEntity<List<AlunoTurmaDTO>> TodosAlunosTurma(@PathVariable Long idTurma){
		List<AlunoTurmaDTO> lista = alunoService.findAllAlunosByTurma(idTurma); 
		return ResponseEntity.ok(lista);}   
	
	@GetMapping("{idTurma}/solicitacaoalunos")
	public ResponseEntity<Page<AlunoTurmaDTO>> TodosAlunosSolicitados(@PathVariable Long idTurma,@RequestParam int pagina,@RequestParam int itens){
		Page<AlunoTurmaDTO> lista = alunoService.findAllByMatriculaSolicitada(idTurma, pagina, itens); 
		return ResponseEntity.ok(lista);}   
  
	@PostMapping 
    public ResponseEntity<AlunoResponseDTO> insert (@RequestBody @Valid CriarAlunoDTO dto){ 
		
		return ResponseEntity.status(201).body(alunoService.adicionar(dto)); }
	
	@PutMapping ("/{matriculaAluno}")
	public ResponseEntity<AlunoResponseDTO> update (
	    @RequestBody @Valid  CriarAlunoDTO  dto,  @PathVariable Long matriculaAluno){
		
		 return ResponseEntity.ok(alunoService.update(dto, matriculaAluno));    
	}
	
	@GetMapping
	public ResponseEntity<Page<AlunoResponseDTO>> todosAlunos (@RequestParam int pagina, @RequestParam int itens){
		
		Page<AlunoResponseDTO> lista = alunoService.listarTodosAlunos(pagina, itens);
		
		return ResponseEntity.ok(lista);  
		
	}
	
	@GetMapping("/{matriculaAluno}") 
	public ResponseEntity<AlunoResponseDTO> buscarporID (@PathVariable Long matriculaAluno){
		return ResponseEntity.status(200).body(alunoService.buscarPorId(matriculaAluno));  
		
	}
	
	@DeleteMapping( "/{matriculaAluno}")  
	public  ResponseEntity <Void> deletar (@PathVariable Long matriculaAluno ){
		try {
			alunoService.deletar(matriculaAluno);
			return ResponseEntity.noContent().build(); }
			catch (RuntimeException e) {
				return ResponseEntity.notFound().build();
			} 
	}
}  