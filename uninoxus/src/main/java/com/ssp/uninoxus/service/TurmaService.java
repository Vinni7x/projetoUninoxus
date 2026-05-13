package com.ssp.uninoxus.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.CriarTurmaDTO;
import com.ssp.uninoxus.dto.TurmaResponseDTO;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.entities.Curso;
import com.ssp.uninoxus.entities.Disciplina;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Professor;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.StatusMatricula;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.TipoAvaliacao;
import com.ssp.uninoxus.enums.Turno;
import com.ssp.uninoxus.repositories.AvaliacaoRepository;
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
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
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
        Professor professor = professorRepository.findById(dto.MatriculaProfessor())
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
        Professor professor = professorRepository.findById(dto.MatriculaProfessor())
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
            if (matricula.getStatusMatricula() != StatusMatricula.MATRICULADO) {
                continue; // ignora canceladas
            }

            double[] notas = calcularMediaFinal(matricula);
            double mediaFinal = notas[0];
            boolean passouPelaFinal = notas[1] == 1.0;

            double criterioAprovacao = passouPelaFinal ? 6.0 : 7.0;

            matricula.setMediaFinal(mediaFinal);
            matricula.setStatusMatricula(
                mediaFinal >= criterioAprovacao
                    ? StatusMatricula.APROVADO
                    : StatusMatricula.REPROVADO
            );
            matriculaRepository.save(matricula);
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

    // retorna [mediaFinal, passouPelaFinal (0 ou 1)]
    private double[] calcularMediaFinal(Matricula matricula) {
        double p1 = getNota(matricula, TipoAvaliacao.AV1);
        double p2 = getNota(matricula, TipoAvaliacao.AV2);
        double p3 = getNota(matricula, TipoAvaliacao.AV3);

        double media = (p1 + p2 + p3) / 3;

        if (media >= 7) return new double[]{media, 0};

        // tenta reposição
        double reposicao = getNota(matricula, TipoAvaliacao.REPOSICAO);
        if (reposicao > 0) {
            double menorNota = Math.min(p1, Math.min(p2, p3));
            if (reposicao > menorNota) {
                media = substituiMenorNota(p1, p2, p3, reposicao);
            }
        }

        if (media >= 7) return new double[]{media, 0};

        // tenta final
        double notaFinal = getNota(matricula, TipoAvaliacao.FINAL);
        if (notaFinal == 0) return new double[]{media, 0}; // não fez final → reprova

        double mediaComFinal = (media + notaFinal) / 2;
        return new double[]{mediaComFinal, 1}; // passou pela final
    }
 
    private double getNota(Matricula matricula, TipoAvaliacao tipoAvaliacao) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository
            .findByMatricula_IdMatriculaAndTipoAvaliacao(matricula.getIdMatricula(), tipoAvaliacao);
        
        if (avaliacao.isEmpty()) return 0.0;
        
        Double nota = avaliacao.get().getNota();  
        return nota != null ? nota : 0.0;
    }


    private double substituiMenorNota(double p1, double p2, double p3, double reposicao) {
        if (p1 <= p2 && p1 <= p3) return (reposicao + p2 + p3) / 3;
        if (p2 <= p1 && p2 <= p3) return (p1 + reposicao + p3) / 3;
        return (p1 + p2 + reposicao) / 3;
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
            turma.getCurso().getIdCurso(),
            turma.getDisciplina().getIdDisciplina(),
            turma.getProfessor().getMatriculaProfessor()   
        );
    }
}