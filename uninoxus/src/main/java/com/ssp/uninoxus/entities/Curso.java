package com.ssp.uninoxus.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso") 
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
    private String nomeCurso;
    private Integer cargaHorariaTotal;
   
    @OneToMany(mappedBy = "curso")
    private Set<Turma> turmas = new HashSet<>();
    
    @OneToMany(mappedBy = "curso")
    private Set<Aluno> alunos = new HashSet<>();
    
    @OneToMany(mappedBy = "curso") 
    private Set<Disciplina> disciplinas = new HashSet<>();
    
    @OneToMany(mappedBy = "curso")
    private Set<Professor> professores = new HashSet<>();
    

	

	public Curso() {
		
	}

	public Curso(Long idCurso, String nomeCurso, Integer cargaHorariaTotal, Set<Turma> turmas ,
			Set<Aluno> alunos,Set<Professor> professores, Set<Disciplina> disciplinas) {
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
		this.cargaHorariaTotal = cargaHorariaTotal;
		this.professores = professores;
		this.alunos = alunos;
		this.disciplinas = disciplinas;
		this.turmas = turmas;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Integer getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Set<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Set<Professor> professores) {
		this.professores = professores;
	}
    

    
}
