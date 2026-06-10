package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.uninoxus.dto.CriarMatriculaDTO;
import com.ssp.uninoxus.dto.MatriculaResponseDTO;
import com.ssp.uninoxus.dto.NotaAlunoDTO;
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

import jakarta.transaction.Transactional;

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
	
	  
	@Transactional
	public MatriculaResponseDTO adicionar(CriarMatriculaDTO dto) {
	    Turma turma = turmaRepository.findById(dto.idTurma())
	            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));
	            
	    Aluno aluno = alunoRepository.findById(dto.matriculaAluno())
	            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado!"));
	     
	    if (turma.getStatusTurma() != StatusTurma.ABERTA) {
	        throw new IllegalArgumentException("Turma não está aberta para matrículas!");
	    }
	     
	    if (matriculaRepository.existsByAluno_MatriculaAlunoAndTurma_IdTurma(aluno.getMatriculaAluno(), turma.getIdTurma())) {
	        throw new IllegalArgumentException("Aluno já solicitou a matricula nessa turma!");
	    }
	       
	    Matricula matricula = new Matricula();
	    matricula.setAluno(aluno);
	    matricula.setTurma(turma);
	    matricula.setStatusMatricula(StatusMatricula.SOLICITADA);

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
	    Double p1 = getNotaNullable(idMatricula, TipoAvaliacao.AV1);
	    
	    if (p1 != null) {
	        throw new IllegalArgumentException("Não é possível cancelar a primeira nota já foi lançada!");
	    }

	    matricula.setStatusMatricula(StatusMatricula.CANCELADA);
	    matriculaRepository.save(matricula);
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
	 
	 
	 public List <NotaAlunoDTO> verNotasAluno (Long matriculaAluno) {
		 
		 List<StatusMatricula> statusAlvo = List.of( 
				    StatusMatricula.MATRICULADO, 
				    StatusMatricula.APROVADO, 
				    StatusMatricula.REPROVADO
				);
		 
		List<Matricula> matriculas = matriculaRepository
			        .findByAluno_MatriculaAlunoAndStatusMatriculaIn(matriculaAluno, statusAlvo);
			    
			    List<NotaAlunoDTO> lista = new ArrayList<>();
 
			    for (Matricula m : matriculas) {  
			        NotaAlunoDTO dto = new NotaAlunoDTO(
			            m.getTurma().getDisciplina().getNomeDisciplina(),
			            getNotaNullable(m.getIdMatricula(), TipoAvaliacao.AV1),
			            getNotaNullable(m.getIdMatricula(), TipoAvaliacao.AV2),
			            getNotaNullable(m.getIdMatricula(), TipoAvaliacao.AV3),
			            getNotaNullable(m.getIdMatricula(), TipoAvaliacao.REPOSICAO),
			            getNotaNullable(m.getIdMatricula(), TipoAvaliacao.FINAL),
			            m.getMediaFinal(),
			            m.getStatusMatricula()
			        );
			        lista.add(dto);
			    }

			    return lista; 
		 
	 }
	 @Transactional
	 public void consolidarMatricula(Long idMatricula) {
	     Matricula matricula = matriculaRepository.findById(idMatricula)
	         .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada!"));

	     if (matricula.getTurma().getStatusTurma() != StatusTurma.CONSOLIDADA) {
	         throw new IllegalArgumentException("Turma ainda não foi consolidada!");
	     }

	     Double p1 = getNotaNullable(idMatricula, TipoAvaliacao.AV1);
	     Double p2 = getNotaNullable(idMatricula, TipoAvaliacao.AV2);
	     Double p3 = getNotaNullable(idMatricula, TipoAvaliacao.AV3);

	     double media = (p1 + p2 + p3) / 3;

	     Double reposicao = getNotaNullable(idMatricula, TipoAvaliacao.REPOSICAO);
	     double menorNota = Math.min(p1, Math.min(p2, p3));

	     if (media < 7 && reposicao != null && reposicao > menorNota) {
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
	         double mediaComFinal = (media + notaFinal) / 2;
	         media = mediaComFinal;
	         matricula.setStatusMatricula(mediaComFinal >= 6 ? StatusMatricula.APROVADO : StatusMatricula.REPROVADO);
	     } else {
	         matricula.setStatusMatricula(StatusMatricula.APROVADO);
	     }

	     matricula.setMediaFinal(media);
	     matriculaRepository.save(matricula);
	     atualizarCR(matricula.getAluno(), media); 
	 }
	 
	 private void atualizarCR(Aluno aluno, Double mediaFinal) {
		    System.out.println(">>> atualizarCR chamado para aluno: " + aluno.getMatriculaAluno());
		    
		    Aluno alunoAtualizado = alunoRepository.findById(aluno.getMatriculaAluno())
		        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado!"));

		    System.out.println(">>> Aluno encontrado: " + alunoAtualizado.getNomePessoa());
		    System.out.println(">>> CR atual: " + alunoAtualizado.getRedimentoAcademico());

		    List<StatusMatricula> statusConsolidados = List.of(
		        StatusMatricula.APROVADO,
		        StatusMatricula.REPROVADO
		    );

		    long totalConsolidadas = matriculaRepository
		        .countByAluno_MatriculaAlunoAndStatusMatriculaIn(alunoAtualizado.getMatriculaAluno(), statusConsolidados);

		    System.out.println(">>> Total consolidadas: " + totalConsolidadas);

		    Double crAtual = alunoAtualizado.getRedimentoAcademico();
		    if (crAtual == null) crAtual = 0.0;

		    Double novoCR = (crAtual * (totalConsolidadas - 1) + mediaFinal) / totalConsolidadas;
		    System.out.println(">>> Novo CR calculado: " + novoCR);

		    alunoAtualizado.setRedimentoAcademico(novoCR);
		    alunoRepository.save(alunoAtualizado);
		    
		    System.out.println(">>> Save executado. CR salvo: " + alunoAtualizado.getRedimentoAcademico());
		}
	 
	 public void autorizarMatricula (Long idMatricula) {
		 Matricula matricula = matriculaRepository.findById(idMatricula) 
 	            .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));
 	 
        matricula.setStatusMatricula(StatusMatricula.MATRICULADO);
        
        matriculaRepository.save(matricula);  
 }
		 
		
	 public void deletar(Long idMatricula) {
	        if (!matriculaRepository.existsById(idMatricula)) {
	            throw new IllegalArgumentException("Matricula não encontrada, impossível apagar!");
	        }
	        matriculaRepository.deleteById(idMatricula); 
	    }
	 
	 
	
	 private MatriculaResponseDTO toDTO(Matricula matricula) {
	        return new MatriculaResponseDTO(
	            matricula.getIdMatricula(),
	            matricula.getMediaFinal(),
	            matricula.getStatusMatricula() 
	          
	        );
	    }

} 
 