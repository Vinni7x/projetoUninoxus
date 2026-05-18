package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarTurmaDTO;
import com.ssp.uninoxus.dto.TurmaMatriculadoDTO;
import com.ssp.uninoxus.dto.TurmaResponseDTO;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.DiasSemana;
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
        turma.setStatusTurma(dto.statusTurma());
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
        turma.setStatusTurma(dto.statusTurma());
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
    }
    
   
    

    public void deletar(Long idTurma) {
        if (idTurma != null && turmaRepository.existsById(idTurma)) {
            turmaRepository.deleteById(idTurma);
        } else {
            throw new IllegalArgumentException("Turma não encontrada, impossível apagar!");
        }
    }
    
    public List<TurmaMatriculadoDTO> turmasMatriculado(Long idMatriculaAluno) {
        List<Matricula> matriculas = matriculaRepository
            .findAllByAluno_MatriculaAlunoAndStatusMatricula(idMatriculaAluno, StatusMatricula.MATRICULADO);

        List<TurmaMatriculadoDTO> lista = new ArrayList<>();
        
        for (Matricula m : matriculas) {
        	Turma turma = m.getTurma(); 
         if (turma.getStatusTurma() != StatusTurma.CONSOLIDADA) {
           
            lista.add(new TurmaMatriculadoDTO(
                turma.getTurno(),
                turma.getHorarioInicio(),
                turma.getHorarioFinal(),
                turma.getLocal(),
                turma.getDiasSemana().toArray(new DiasSemana[0]),
                turma.getStatusTurma()
            ));}
        }
        return lista;
    }
    
    private TurmaResponseDTO toDTO(Turma turma) {
        return new TurmaResponseDTO(
         
            turma.getSemestre(),
            turma.getTurno(),
            turma.getHorarioInicio(),
            turma.getHorarioFinal(),
            turma.getLocal(),
            turma.getVagas(),
            turma.getDiasSemana(),
            turma.getStatusTurma(),
            turma.getCurso().getIdCurso(),
            turma.getDisciplina().getIdDisciplina(),
            turma.getProfessor().getMatriculaProfessor()   
        );
    }
}