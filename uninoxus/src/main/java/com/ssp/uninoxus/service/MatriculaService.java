package com.ssp.uninoxus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarMatriculaDTO;
import com.ssp.uninoxus.dto.MatriculaResponseDTO;
import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.repositories.AlunoRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;
import com.ssp.uninoxus.repositories.TurmaRepository;

@Service
public class MatriculaService {
	
	@Autowired 
	private MatriculaRepository matriculaRepository;
	@Autowired 
	private TurmaRepository turmaRepository;
	@Autowired 
	private AlunoRepository alunoRepository;
	  
	public MatriculaResponseDTO adicionar (CriarMatriculaDTO dto) {
		 Turma turma = turmaRepository.findById(dto.idTurma())
		            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado!"));
		 Aluno aluno = alunoRepository.findById(dto.matriculaAluno())
		            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrada!"));
		 
		  if (turma.getStatusTurma() != StatusTurma.ABERTA) {
	            throw new IllegalArgumentException("Turma não está aberta para matrículas!");
	        }
		  long matriculasAtivas = 0;
		  for (Matricula m : turma.getMatriculas()) {
		      if (m.getStatusMatricula() == StatusMatricula.MATRICULADO) {
		          matriculasAtivas++;
		      }
		  }
		  
		  if (matriculasAtivas >= turma.getVagas()) {
	            throw new IllegalArgumentException("Turma sem vagas disponíveis!");
	        }
		  
		  if (matriculaRepository.existsByAluno_MatriculaAlunoAndTurma_IdTurma(aluno.getMatriculaAluno(), turma.getIdTurma())) {
	            throw new IllegalArgumentException("Aluno já matriculado nessa turma!");
	        }
		  
		  
		   Matricula matricula = new Matricula();
	        matricula.setAluno(aluno);
	        matricula.setTurma(turma);
	        matricula.setStatusMatricula(StatusMatricula.MATRICULADO);
	        matricula.setMediaFinal(0.0);
	        matricula.setFrequencia(0.0);
 
	        matriculaRepository.save(matricula);
	        return toDTO(matricula);	 
	}
	
	
	public void cancelar(Long idMatricula) {
	    Matricula matricula = matriculaRepository.findById(idMatricula)
	        .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada!"));

	    if (matricula.getStatusMatricula() == StatusMatricula.CANCELADA) {
	        throw new IllegalArgumentException("Matrícula já está cancelada!");
	    }

	    if (matricula.getStatusMatricula() != StatusMatricula.MATRICULADO) {
	        throw new IllegalArgumentException("Não é possível cancelar uma matrícula já consolidada!");
	    }

	    matricula.setStatusMatricula(StatusMatricula.CANCELADA);
	    matriculaRepository.save(matricula);
	} 
	
	 public List<MatriculaResponseDTO> todasMatriculas(Long matriculaAluno) {
    	 List<Matricula> matriculas = matriculaRepository.findAllByAluno_MatriculaAluno(matriculaAluno);
    	     
    	    List<MatriculaResponseDTO> lista = new ArrayList<>(); 
     	    for (Matricula m : matriculas) {
    	        lista.add(toDTO(m));  
    	    } 
    	    return lista; 
    	}    
	
	
	 private MatriculaResponseDTO toDTO(Matricula matricula) {
	        return new MatriculaResponseDTO(
	            matricula.getIdMatricula(),
	            matricula.getMediaFinal(),
	            matricula.getFrequencia(),
	            matricula.getStatusMatricula()
	          
	        );
	    }

}
