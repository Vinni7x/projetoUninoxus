package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.AlunoResponseDTO;
import com.ssp.uninoxus.dto.AlunoTurmaDTO;
import com.ssp.uninoxus.dto.CriarAlunoDTO;
import com.ssp.uninoxus.entities.Aluno;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.repositories.AlunoRepository;
import com.ssp.uninoxus.repositories.CursoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	public List<AlunoTurmaDTO> findAllAlunosByTurma(Long idTurma) {
	    List<Aluno> alunos = alunoRepository.findByMatriculas_Turma_IdTurma(idTurma);
	    List<AlunoTurmaDTO> lista = new ArrayList<>(); 

	    for (Aluno a : alunos) {
	        for (Matricula m : a.getMatricula()) {
	            if (m.getTurma().getIdTurma().equals(idTurma)
	                    && m.getStatusMatricula() == StatusMatricula.MATRICULADO) {
	            	lista.add(toDTOAlunoTurma(m));
	                break; 
	            }
	        }
	    }

	    return lista;
	}
	
	public List<AlunoTurmaDTO> findAllAlunosSolicitados(Long idTurma) {
	    List<Aluno> alunos = alunoRepository.findByMatriculas_Turma_IdTurma(idTurma);
	    List<AlunoTurmaDTO> lista = new ArrayList<>();
 
	    for (Aluno a : alunos) {
	        for (Matricula m : a.getMatricula()) {
	            if (m.getTurma().getIdTurma().equals(idTurma)
	                    && m.getStatusMatricula() == StatusMatricula.SOLICITADA) {
	                lista.add(toDTOAlunoTurma(m));
	                break; 
	            } 
	        } 
	    }

	    return lista;
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
	 
	 public AlunoResponseDTO buscarPorId(Long matriculaAluno) {
		    if (matriculaAluno == null) {
		        throw new IllegalArgumentException("A matrícula do aluno não pode ser nula!");
		    } 
		    
		    Aluno aluno = alunoRepository.findById(matriculaAluno)
		        .orElseThrow(() -> new IllegalArgumentException("Aluno com matrícula " + matriculaAluno + " não encontrado!"));
		        
		  
		    return toDTO(aluno);    
		}
		public Page<AlunoTurmaDTO> findAllByMatriculaSolicitada(Long idTurma, int pagina, int itens) {
			PageRequest pageRequest = PageRequest.of(pagina, itens);
		    Page<Aluno> alunos = alunoRepository.findByMatriculas_Turma_IdTurma(idTurma, pageRequest); 
		    List<AlunoTurmaDTO> lista = new ArrayList<>();
 
		    for (Aluno a : alunos) {
		        for (Matricula m : a.getMatricula()) {
		            if (m.getTurma().getIdTurma().equals(idTurma)
		                    && m.getStatusMatricula() == StatusMatricula.SOLICITADA) {
		                lista.add(toDTOAlunoTurma(m)); 
		                break; 
		            }
		        }
		    }

		    return new PageImpl<>(lista, pageRequest, alunos.getTotalElements());
		} 
  
		 public void deletar(Long matriculaAluno) {
		        if (!alunoRepository.existsById(matriculaAluno)) {
		            throw new IllegalArgumentException("Aluno não encontrado, impossível apagar!");
		        }
		        alunoRepository.deleteById(matriculaAluno); 
		    }
		 
		 public Page<AlunoResponseDTO> listarTodosAlunos(int pagina, int itens){
			 PageRequest pageRequest = PageRequest.of(pagina, itens);
			 Page<Aluno> alunos = alunoRepository.findAll(pageRequest);
			 List<AlunoResponseDTO> lista = new ArrayList<>();
			 
			 for(Aluno a: alunos) { 
				 lista.add(toDTO(a)); 
			 }
			 return new PageImpl<>(lista, pageRequest, alunos.getTotalElements());
		 }
	 
	 private AlunoResponseDTO toDTO(Aluno aluno) { 
	        return new AlunoResponseDTO( 
	        aluno.getMatriculaAluno(),
	        aluno.getNomePessoa(),
	        aluno.getRedimentoAcademico(),
	        aluno.getCurso().getNomeCurso(),
	        aluno.getCurso().getIdCurso()
	        
	        ); 
	    }
	 
	 
	 private AlunoTurmaDTO toDTOAlunoTurma(Matricula matricula) {
		 
		return new AlunoTurmaDTO(
                 matricula.getIdMatricula(),
                 matricula.getTurma().getIdTurma(),
                 matricula.getAluno().getNomePessoa()); 
		 
	 } 
	
}
