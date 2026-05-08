package com.ssp.uninoxus.entities;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.Turno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table( name = "turma") 
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTurma;
	@Column( nullable = false) 
    private String semestre;
	@Column( nullable = false) 
    private Turno turno;
	@Column( nullable = false) 
    private LocalTime horarioInicio; 
	@Column( nullable = false) 
    private LocalTime horarioFinal;
	@Column( nullable = false) 
    private String local; 
	@Column( nullable = false) 
    private Integer vagas;
	@Column( nullable = false) 
    private List <DiasSemana> diasSemana = new ArrayList<>();
    @Column( nullable = false) 
    private StatusTurma statusTurma; 
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_curso", nullable = false) 
    private Curso curso;  
    
     
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_disciplina", nullable = false) 
    private Disciplina disciplina;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_professor", nullable = false) 
    private Professor professor;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
	private Set<Matricula> matriculas = new HashSet<>(); 
   
    
    
    public Turma () {}

	public Turma(Long idTurma, String semestre, Turno turno, LocalTime horarioInicio,
			LocalTime horarioFinal,String local, Integer vagas, Double mediaFinal,
			StatusTurma statusTurma, Curso curso, Professor professor, 
			Disciplina disciplina, Set<Matricula> matriculas ) {
		super();
		this.idTurma = idTurma;
		this.semestre = semestre;
		this.turno = turno;
		this.horarioInicio = horarioInicio;
		this.horarioFinal = horarioFinal;
		this.local = local;
		this.vagas = vagas;
		this.statusTurma = statusTurma;
		this.curso = curso;
		this.professor = professor;
		this.disciplina = disciplina; 
		this.matriculas = matriculas;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
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

	public StatusTurma getStatusTurma() {
		return statusTurma;
	}

	public void setStatusTurma(StatusTurma statusTurma) {
		this.statusTurma = statusTurma;
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
     
}
