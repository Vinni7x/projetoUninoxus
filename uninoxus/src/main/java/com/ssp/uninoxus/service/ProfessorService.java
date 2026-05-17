package com.ssp.uninoxus.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.uninoxus.dto.CriarProfessorDTO;
import com.ssp.uninoxus.dto.ProfessorResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.repositories.CursoRepository;
import com.ssp.uninoxus.repositories.ProfessorRepository; 

@Service
public class ProfessorService {

	
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private CursoRepository cursoRepository;

	public List <Professor> findAll(){ 
		return professorRepository.findAll();
	}
	
	public ProfessorResponseDTO adicionar (CriarProfessorDTO dto){
		 
		 boolean professorJaExiste = professorRepository.existsByCpf(dto.cpf()); 
		   if (professorJaExiste) { 
			    throw new IllegalArgumentException("Esse Professor já foi cadastrado!");
			}
		   
		   Curso curso = cursoRepository.findById(dto.idCurso())
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com o ID fornecido!"));
		   
		   Professor professor = new Professor();
		    professor.setNomePessoa(dto.nomePessoa());
		    professor.setCpf(dto.cpf());
		    professor.setDataNascimento(dto.dataNascimento());
		    professor.setTitulacao(dto.titulacao());
		    professor.setEspecializacao(dto.especializacao());
		
		    professor.setCurso(curso); 

		  
		    professorRepository.save(professor);
		    return toDTO(professor);
		
	}
	 
	 public ProfessorResponseDTO update (CriarProfessorDTO dto, Long matriculaProfessor) {
		  
		   Professor professorExistente = professorRepository.findById(matriculaProfessor)
		            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrada!")); 
		   
		   Curso curso = cursoRepository.findById(dto.idCurso())
		            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
		    
		   if (!professorExistente.getCpf().equals(dto.cpf())) {  
			   boolean cpfJaExiste = professorRepository.existsByCpf(dto.cpf());
		        if (cpfJaExiste) { 
		            throw new IllegalArgumentException("Já existe outro professor cadastrado com este cpf!");
		        }
		   }
		   
		   professorExistente.setNomePessoa(dto.nomePessoa());
		   professorExistente.setCpf(dto.cpf()); 
		   professorExistente.setDataNascimento(dto.dataNascimento());
		   professorExistente.setTitulacao(dto.titulacao());
		   professorExistente.setEspecializacao(dto.especializacao());
		   professorExistente.setCurso(curso);   
			 
	        professorRepository.save(professorExistente); 
	        return toDTO(professorExistente);	
	 } 
	
	 
	 private ProfessorResponseDTO toDTO(Professor professor) { 
	        return new ProfessorResponseDTO(
	        professor.getNomePessoa(),  
	        professor.getCurso().getNomeCurso()
	        ); 
	    }
	
} 


