package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarTurmaDTO;
import com.ssp.uninoxus.dto.TurmaMatriculadoDTO;
import com.ssp.uninoxus.dto.TurmaMinistradaDTO;
import com.ssp.uninoxus.dto.TurmaResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.repositories.CursoRepository;
import com.ssp.uninoxus.repositories.DisciplinaRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;
import com.ssp.uninoxus.repositories.ProfessorRepository;
import com.ssp.uninoxus.repositories.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public TurmaResponseDTO adicionar(CriarTurmaDTO dto) {
        Curso curso = cursoRepository.findById(dto.idCurso())
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
        Disciplina disciplina = disciplinaRepository.findById(dto.idDisciplina())
            .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));
        Professor professor = professorRepository.findById(dto.matriculaProfessor())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado!"));
        
        Turma turma = new Turma(); 
        turma.setSemestre(dto.semestre());
        turma.setTurno(dto.turno());
        turma.setHorarioInicio(dto.horarioInicio()); 
        turma.setHorarioFinal(dto.horarioFinal());
        turma.setLocal(dto.local());
        turma.setVagas(dto.vagas());
        turma.setDiasSemana(dto.diasSemana());
        turma.setStatusTurma(StatusTurma.ABERTA);
        turma.setCurso(curso);
        turma.setDisciplina(disciplina);
        turma.setProfessor(professor); 

        turmaRepository.save(turma);
        return toDTO(turma); 
    }

    public TurmaResponseDTO update(CriarTurmaDTO dto, Long idTurma) {
        Turma turma = turmaRepository.findById(idTurma)
            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));
        Curso curso = cursoRepository.findById(dto.idCurso())
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
        Disciplina disciplina = disciplinaRepository.findById(dto.idDisciplina())
            .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));
        Professor professor = professorRepository.findById(dto.matriculaProfessor())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado!"));

        turma.setSemestre(dto.semestre());
        turma.setTurno(dto.turno());
        turma.setHorarioInicio(dto.horarioInicio());
        turma.setHorarioFinal(dto.horarioFinal());
        turma.setLocal(dto.local());
        turma.setVagas(dto.vagas());
        turma.setDiasSemana(dto.diasSemana());
        turma.setStatusTurma(StatusTurma.ABERTA);
        turma.setCurso(curso); 
        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);

        turmaRepository.save(turma);
        return toDTO(turma);
    }
 
    public void consolidar(Long idTurma) {
        Turma turma = turmaRepository.findById(idTurma)
            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        if (turma.getStatusTurma() == StatusTurma.CONSOLIDADA) {
            throw new IllegalArgumentException("Turma já foi consolidada!");
        }

        for (Matricula matricula : turma.getMatriculas()) {
            if (matricula.getStatusMatricula() == StatusMatricula.MATRICULADO) {
                boolean valido = matriculaService.validarAvaliacoesNotasParaConsolidacao(matricula.getIdMatricula());
                if (!valido) {
                    throw new IllegalArgumentException(
                        "Aluno " + matricula.getAluno().getMatriculaAluno() + " possui notas pendentes!"
                    );
                }
            }
        } 
        
        turma.setStatusTurma(StatusTurma.CONSOLIDADA);
        turmaRepository.save(turma);

        for (Matricula matricula : turma.getMatriculas()) {
            if (matricula.getStatusMatricula() == StatusMatricula.MATRICULADO) {
                matriculaService.consolidarMatricula(matricula.getIdMatricula());
            }
        }
    }
    
   
    
    public List<TurmaMatriculadoDTO> turmasMatriculado(Long matriculaAluno) {
        List<Matricula> matriculas = matriculaRepository
            .findByAluno_MatriculaAlunoAndStatusMatricula(matriculaAluno, StatusMatricula.MATRICULADO);

        List<TurmaMatriculadoDTO> lista = new ArrayList<>();
        for (Matricula m : matriculas) {
            Turma t = m.getTurma();
            if (t.getStatusTurma() != StatusTurma.CONSOLIDADA) {
                lista.add(new TurmaMatriculadoDTO(
                    m.getIdMatricula(),
                    t.getDisciplina().getNomeDisciplina(),
                    t.getTurno(),
                    t.getHorarioInicio(),
                    t.getHorarioFinal(),
                    t.getLocal(),
                    t.getDiasSemana(), 
                    t.getStatusTurma() 
                ));
            }
        } 
        return lista;
    }
     
    public List<TurmaResponseDTO> verTurmasAbertas(Long idCurso, Long matriculaAluno) {
        List<Turma> turmasAbertas = turmaRepository
            .findByCursoIdCursoAndStatusTurma(idCurso, StatusTurma.ABERTA);

        List<TurmaResponseDTO> lista = new ArrayList<>();

        for (Turma turma : turmasAbertas) {
            boolean alunoJaSolicitou = false;

            for (Matricula m : turma.getMatriculas()) {
                if (m.getAluno().getMatriculaAluno().equals(matriculaAluno) 
                    && m.getStatusMatricula() == StatusMatricula.SOLICITADA 
                    || m.getStatusMatricula() == StatusMatricula.MATRICULADO ) {
                    alunoJaSolicitou = true;
                    break; 
                }
            }
  
            if (!alunoJaSolicitou) {
                lista.add(toDTO(turma));
            }
        }

        return lista;
    }
    
    public List<TurmaMinistradaDTO> turmasMinistradas(Long matriculaProfessor) {
    	    List<Turma> turmas = turmaRepository.findByProfessorMatriculaProfessor(matriculaProfessor);

    	    List<TurmaMinistradaDTO> lista = new ArrayList<>();  
    	    for (Turma t : turmas) {  
    	        if (t.getStatusTurma() != StatusTurma.CONSOLIDADA) {
    	           
    	            lista.add(new TurmaMinistradaDTO (
    	            	t.getIdTurma(),
    	                t.getDisciplina().getNomeDisciplina(),   
    	                t.getTurno(),
    	                t.getHorarioInicio(),
    	                t.getHorarioFinal(),
    	                t.getLocal(),
    	                t.getDiasSemana(),
    	                t.getStatusTurma()
    	            ));
    	        }
    	    } 
    	    return lista;
    	}
    
    public List<TurmaResponseDTO> listarPorCurso(Long idCurso) { 
		List<Turma> turmas = turmaRepository.findByCurso_IdCurso(idCurso);
		List<TurmaResponseDTO> lista = new ArrayList<>();
		 
		for(Turma t: turmas) {
			lista.add(toDTO(t));
		}
		return lista;   
	}
    	  
    public TurmaResponseDTO listarPorId(Long idTurma){
    	Turma turma = turmaRepository.findById(idTurma)
    			.orElseThrow(() -> new IllegalArgumentException("Turma" + idTurma + " não encontrado!"));
        ;
    	return toDTO(turma); 
    } 

    

    public void deletar(Long idTurma) {
        if (idTurma != null && turmaRepository.existsById(idTurma)) {
            turmaRepository.deleteById(idTurma);
        } else {
            throw new IllegalArgumentException("Turma não encontrada, impossível apagar!");
        }
    }
    	
	 public Page<TurmaResponseDTO> listarTodasTurmas(int pagina, int itens){
		 PageRequest pageRequest = PageRequest.of(pagina, itens);
		 Page<Turma> turmas = turmaRepository.findAll(pageRequest);
		 List<TurmaResponseDTO> lista = new ArrayList<>();
		 
		 for(Turma t: turmas) { 
			 lista.add(toDTO(t));
		 }
		 return new PageImpl<>(lista, pageRequest, turmas.getTotalElements());
	 }

    
    private TurmaResponseDTO toDTO(Turma turma) {
        return new TurmaResponseDTO(
            turma.getIdTurma(),
            turma.getSemestre(),
            turma.getTurno(),
            turma.getHorarioInicio(),
            turma.getHorarioFinal(),
            turma.getLocal(),
            turma.getVagas(),
            turma.getDiasSemana(),
            turma.getStatusTurma(),
            turma.getCurso().getNomeCurso(),
            turma.getCurso().getIdCurso(), 
            turma.getDisciplina().getNomeDisciplina(),
            turma.getDisciplina().getIdDisciplina(),
            turma.getProfessor().getNomePessoa(),
            turma.getProfessor().getMatriculaProfessor() 
        );
    }

}