package com.ssp.uninoxus.entities;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ssp.uninoxus.enumeration.DiasSemana;
import com.ssp.uninoxus.enumeration.StatusMatricula;
import com.ssp.uninoxus.enumeration.StatusTurma;
import com.ssp.uninoxus.enumeration.Turno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table( name = "turma") 
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTurma; 
    private String semestre;
    private Turno turno;
    private LocalTime horarioInicio; 
    private LocalTime horarioFinal;
    private String local; 
    private Integer vagas;
    private List <DiasSemana> diasSemana = new ArrayList<>();;
    private Double mediaFinal;
    private Double frequencia;
    private StatusTurma statusTurma; 
    private StatusMatricula statusMatricula;
     
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false) 
    private Curso curso;  
    
    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false) 
    private Disciplina disciplina;
    
    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false) 
    private Professor professor;
    
    @ManyToMany
	@JoinTable(name = "aluno_turma", joinColumns = @JoinColumn (name = "id_turma"), 
	inverseJoinColumns = @JoinColumn (name = "id_aluno" ))  
	private Set<Aluno> alunos = new HashSet<>();   
    
    
    public Turma () {}

	public Turma(Long idTurma, String semestre, Turno turno, LocalTime horarioInicio, LocalTime horarioFinal,
			String local, Integer vagas, Double mediaFinal, Double frequencia,
			StatusTurma statusTurma, StatusMatricula statusMatricula, Curso curso, Professor professor, Disciplina disciplina) {
		super();
		this.idTurma = idTurma;
		this.semestre = semestre;
		this.turno = turno;
		this.horarioInicio = horarioInicio;
		this.horarioFinal = horarioFinal;
		this.local = local;
		this.vagas = vagas;
		this.mediaFinal = mediaFinal;
		this.frequencia = frequencia;
		this.statusTurma = statusTurma;
		this.statusMatricula = statusMatricula;
		this.curso = curso;
		this.professor = professor;
		this.disciplina = disciplina; 
	}

	

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public LocalTime getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(LocalTime horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public List <DiasSemana> getDiasSemana() {
		return diasSemana;
	}
 
	public void setDiasSemana(List <DiasSemana> diasSemana) {
		this.diasSemana = diasSemana; 
	}

	public Double getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(Double mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public Double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Double frequencia) {
		this.frequencia = frequencia;
	}

	public StatusTurma getStatusTurma() {
		return statusTurma;
	}

	public void setStatusTurma(StatusTurma statusTurma) {
		this.statusTurma = statusTurma;
	}

	public StatusMatricula getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(StatusMatricula statusMatricula) {
		this.statusMatricula = statusMatricula;
	}

	public Long getIdTurma() {
		return idTurma;
	}
    
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Aluno> getAluno() {
		return alunos;
	}

	public void setAluno(Set<Aluno> alunos) {
		this.alunos = alunos;  
	} 
     
}
