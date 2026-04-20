package com.ssp.uninoxus.entities;
import java.time.LocalDate;

import com.ssp.uninoxus.enumeration.Titulacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Professor extends Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculaProfessor;
	private Titulacao titulacao;
	private String especializacao;
	
	@ManyToOne
    @JoinColumn(name = "id_curso") 
	private Curso curso;
	
	public Professor() {
		super();
		
	}



	public Professor(Long nomePessoa, String cpf, LocalDate dataNascimento, Long matriculaProfessor, Titulacao titulacao, String especializacao) {
		super(nomePessoa, cpf, dataNascimento);
		this.matriculaProfessor = matriculaProfessor;
		this.titulacao = titulacao;
		this.especializacao = especializacao; 
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

	

}
