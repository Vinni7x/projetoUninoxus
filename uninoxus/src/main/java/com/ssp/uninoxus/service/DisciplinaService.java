package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssp.uninoxus.dto.CriarDisciplinaDTO;
import com.ssp.uninoxus.dto.DisciplinaResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.repositories.CursoRepository;
import com.ssp.uninoxus.repositories.DisciplinaRepository;


@Service
public class DisciplinaService {

		@Autowired
		private DisciplinaRepository disciplinaRepository; 
		@Autowired
		private CursoRepository cursoRepository; 
		
		public List<DisciplinaResponseDTO> listarPorCurso(Long idCurso) { 
			List<Disciplina> disciplinas = disciplinaRepository.findByCurso_IdCurso(idCurso);
			List<DisciplinaResponseDTO> lista = new ArrayList<>();
			  
			for(Disciplina d: disciplinas) {
				lista.add(toDTO(d));
			}
			return lista;  
		} 
			
		public DisciplinaResponseDTO adicionar (CriarDisciplinaDTO dto) {  
			  boolean disciplinaJaExiste = disciplinaRepository.existsByNomeDisciplinaIgnoreCase(dto.nomeDisciplina());
			   if (disciplinaJaExiste) {
				    throw new IllegalArgumentException("Essa Disciplina já existe!"); 
				}  
			   
			   Curso curso = cursoRepository.findById(dto.idCurso())
			            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
		
			 Disciplina disciplina = new Disciplina(); 
		        disciplina.setNomeDisciplina(dto.nomeDisciplina()); 
		        disciplina.setCargaHoraria(dto.cargaHoraria());
		        disciplina.setCurso(curso);  
		       
		        disciplinaRepository.save(disciplina);
		        return toDTO(disciplina);  
			}
		
		public List<DisciplinaResponseDTO> listarTodasDisciplina (){
			List<Disciplina> disciplinas = disciplinaRepository.findAll();
			 List<DisciplinaResponseDTO> lista = new ArrayList<>();
			 
			 for(Disciplina d: disciplinas) {
				 lista.add(toDTO(d));
			 }
			 return lista; 
		}
		
		 public DisciplinaResponseDTO listarPorId(Long idDisciplina){ 
		    	Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
		    			.orElseThrow(() -> new IllegalArgumentException("Disciplina" + idDisciplina+ " não encontrado!"));
		        ;
		    	return toDTO(disciplina); 
		    } 
		
		 public void deletar(Long idDisciplina) {
		        if (!disciplinaRepository.existsById(idDisciplina)) {
		            throw new IllegalArgumentException("Disciplina não encontrada, impossível apagar!");
		        }
		        disciplinaRepository.deleteById(idDisciplina); 
		    }
		
		 private DisciplinaResponseDTO toDTO(Disciplina disciplina) {
		        return new DisciplinaResponseDTO(
		        disciplina.getIdDisciplina(),
		        disciplina.getNomeDisciplina(),   
		        disciplina.getCargaHoraria(),
		        disciplina.getCurso().getNomeCurso()
		      
		        		); 
		    }
		
		
		}
		
	 

