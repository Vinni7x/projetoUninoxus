package com.ssp.uninoxus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.AlunoResponseDTO;
import com.ssp.uninoxus.dto.CriarAlunoDTO;
import com.ssp.uninoxus.dto.CriarProfessorDTO;
import com.ssp.uninoxus.dto.ProfessorResponseDTO;
import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.repositories.AlunoRepository;
import com.ssp.uninoxus.repositories.CursoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	public List <Aluno> findAll(){
		return alunoRepository.findAll(); 
	}
	
	public AlunoResponseDTO adicionar (CriarAlunoDTO dto){
		 
		 boolean alunoJaExiste = alunoRepository.existsByCpf(dto.cpf()); 
		   if (alunoJaExiste) { 
			    throw new IllegalArgumentException("Esse Aluno já foi cadastrado!");
			}
		   
		   Curso curso = cursoRepository.findById(dto.idCurso())
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com o ID fornecido!"));
		   
		   Aluno aluno = new Aluno();
		   aluno.setNomePessoa(dto.nomePessoa());
		   aluno.setCpf(dto.cpf());
		   aluno.setDataNascimento(dto.dataNascimento());
		   aluno.setCurso(curso);  

		  
		    alunoRepository.save(aluno);
		    return toDTO(aluno);
		
	}
	
	 public AlunoResponseDTO update (CriarAlunoDTO dto, Long matriculaAluno) { 
		  
		   Aluno alunoExistente = alunoRepository.findById(matriculaAluno)
		            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrada!")); 
		   
		   Curso curso = cursoRepository.findById(dto.idCurso())
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
		    
		   if (!alunoExistente.getCpf().equals(dto.cpf())) {  
			   boolean cpfJaExiste = alunoRepository.existsByCpf(dto.cpf());
		        if (cpfJaExiste) { 
		            throw new IllegalArgumentException("Já existe outro aluno cadastrado com este cpf!");
		        }
		   }
		   
		   alunoExistente.setNomePessoa(dto.nomePessoa());
		   alunoExistente.setCpf(dto.cpf()); 
		   alunoExistente.setDataNascimento(dto.dataNascimento());
		   alunoExistente.setCurso(curso);   
			 
	        alunoRepository.save(alunoExistente); 
	        return toDTO(alunoExistente);	 
	 } 
	 
	 
	 private AlunoResponseDTO toDTO(Aluno aluno) { 
	        return new AlunoResponseDTO( 
	        aluno.getNomePessoa(),
	        aluno.getRedimentoAcademico(),
	        aluno.getCurso().getNomeCurso() 
	        
	        ); 
	    }
	
}
