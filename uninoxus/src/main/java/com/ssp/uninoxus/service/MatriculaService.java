package com.ssp.uninoxus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarMatriculaDTO;
import com.ssp.uninoxus.dto.MatriculaResponseDTO;
import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Nota;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.TipoAvaliacao;
import com.ssp.uninoxus.repositories.AlunoRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;
import com.ssp.uninoxus.repositories.NotaRepository;
import com.ssp.uninoxus.repositories.TurmaRepository;

@Service
public class MatriculaService {
	
	@Autowired 
	private MatriculaRepository matriculaRepository;
	@Autowired 
	private TurmaRepository turmaRepository;
	@Autowired 
	private AlunoRepository alunoRepository;
	@Autowired 
	private NotaRepository notaRepository;
	  
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
	 
	 Double getNotaNullable(Long idMatricula, TipoAvaliacao tipoAvaliacao) {
		    Optional<Nota> nota = notaRepository
		        .findByMatricula_IdMatriculaAndAvaliacao_TipoAvaliacao(idMatricula, tipoAvaliacao);
 
		    if (nota.isEmpty()) {
		        return null;
		    }

		    return nota.get().getNota(); 
		}
	
	 public boolean validarAvaliacoesNotasParaConsolidacao(Long idMatricula) {
		    Double p1 = getNotaNullable(idMatricula, TipoAvaliacao.AV1); 
		    Double p2 = getNotaNullable(idMatricula, TipoAvaliacao.AV2);
		    Double p3 = getNotaNullable(idMatricula, TipoAvaliacao.AV3);    

		    if (p1 == null || p2 == null || p3 == null) {
		        return false;
		    }

		    double media = (p1 + p2 + p3) / 3;

		    if (media < 7) {
		        Double reposicao = getNotaNullable(idMatricula, TipoAvaliacao.REPOSICAO);

		        if (reposicao == null) { 
		            return false;
		        }

		        double menorNota = Math.min(p1, Math.min(p2, p3));
		        if (reposicao > menorNota) {
		            if (p1 <= p2 && p1 <= p3) {
		                media = (reposicao + p2 + p3) / 3;
		            } else if (p2 <= p1 && p2 <= p3) {
		                media = (p1 + reposicao + p3) / 3;
		            } else {
		                media = (p1 + p2 + reposicao) / 3;
		            }
		        }

		        if (media < 7) {
		            Double notaFinal = getNotaNullable(idMatricula, TipoAvaliacao.FINAL);

		            if (notaFinal == null) {
		                return false;
		            }
		        }
		    }
		    
		    return true;
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
