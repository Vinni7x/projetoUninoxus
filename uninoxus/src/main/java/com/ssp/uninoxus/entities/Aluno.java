package com.ssp.uninoxus.entities;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Aluno extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculaAluno;
	private Double redimentoAcademico;
	
	@ManyToOne
	@JoinColumn(name = "id_curso") 
	private Curso curso; 
	
	public Aluno() {
		
	}

	public Aluno(Long nomePessoa, String cpf, LocalDate dataNascimento, Long matriculaAluno, Double redimentoAcademico) {
		super(nomePessoa, cpf, dataNascimento);
		this.matriculaAluno = matriculaAluno;
		this.redimentoAcademico = redimentoAcademico;
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

	@Override
	public int hashCode() {
		return Objects.hash(matriculaAluno);
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(matriculaAluno, other.matriculaAluno);
	}
	

}
