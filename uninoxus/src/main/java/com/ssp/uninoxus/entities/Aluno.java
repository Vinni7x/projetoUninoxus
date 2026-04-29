package com.ssp.uninoxus.entities;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Aluno extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculaAluno;
	private Double redimentoAcademico;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_curso") 
	private Curso curso;  
	
	@OneToMany(mappedBy = "aluno")
	@JsonIgnore
	private Set<Matricula> matriculas = new HashSet<>(); 
	
	public Aluno() {
		
	}

	public Aluno(String nomePessoa, String cpf, LocalDate dataNascimento, Long matriculaAluno, 
			Double redimentoAcademico,Set<Matricula> matriculas) {
		super(nomePessoa, cpf, dataNascimento);
		this.matriculaAluno = matriculaAluno;
		this.redimentoAcademico = redimentoAcademico;
		this.matriculas = matriculas;
	}

	public Curso getCurso() {
		return curso;
	}
 
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Set<Matricula> getMatricula() {
		return matriculas; 
	}

	public void setMatricula(Set<Matricula> matriculas) {
		this.matriculas = matriculas; 
	}

	public Double getRedimentoAcademico() {
		return redimentoAcademico;
	}

	public void setRedimentoAcademico(Double redimentoAcademico) {
		this.redimentoAcademico = redimentoAcademico;
	}

	public Long getMatriculaAluno() {
		return matriculaAluno;
	}

}
