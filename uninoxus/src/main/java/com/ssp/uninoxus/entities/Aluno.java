package com.ssp.uninoxus.entities;
import java.time.LocalDate;

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
	@JoinColumn(name = "id_curso") 
	private Curso curso; 
	
	@OneToMany(mappedBy = "matricula")
	private Matricula matricula; 
	
	public Aluno() {
		
	}

	public Aluno(Long nomePessoa, String cpf, LocalDate dataNascimento, Long matriculaAluno, 
			Double redimentoAcademico,Matricula matricula) {
		super(nomePessoa, cpf, dataNascimento);
		this.matriculaAluno = matriculaAluno;
		this.redimentoAcademico = redimentoAcademico;
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}
 
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
