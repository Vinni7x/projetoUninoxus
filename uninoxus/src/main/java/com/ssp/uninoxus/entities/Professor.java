package com.ssp.uninoxus.entities;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.ssp.uninoxus.enumeration.Titulacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Professor extends Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculaProfessor;
	private Titulacao titulacao;
	private String especializacao;
	
	
	@OneToMany(mappedBy = "professor")
	private Set<Turma> turmas = new HashSet<>();  
	
	@ManyToOne
	private Curso curso;  


	public Professor() {
		super();
		
	}



	public Professor(Long nomePessoa, String cpf, LocalDate dataNascimento, Long matriculaProfessor, 
			Titulacao titulacao, String especializacao, Set<Turma> turmas, Curso curso) {
		super(nomePessoa, cpf, dataNascimento);
		this.matriculaProfessor = matriculaProfessor;
		this.titulacao = titulacao;
		this.especializacao = especializacao; 
		this.turmas = turmas;
		this.curso = curso;
		
	}
 


	public Titulacao getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(Titulacao titulacao) {
		this.titulacao = titulacao;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public Long getMatriculaProfessor() {
		return matriculaProfessor;
	}

	public Set<Turma> getTurma() {
		return turmas;
	}



	public void setTurma(Set<Turma> turmas) {
		this.turmas = turmas;
	} 



	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	

}
