package com.ssp.uninoxus.entities;
import java.util.HashSet; 
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssp.uninoxus.enums.StatusMatricula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "matricula", uniqueConstraints ={
	    @UniqueConstraint(columnNames = {"id_aluno", "id_turma"})})
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatricula; 
	@Column( nullable = false) 
	private Double mediaFinal;
	@Column( nullable = false) 
	private Double frequencia;
	@Column( nullable = false) 
	private StatusMatricula statusMatricula;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_aluno", nullable = false)
	private Aluno aluno;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@OneToMany(mappedBy="matricula")
	private Set<Avaliacao> avaliacoes = new HashSet<>();

	public Matricula() {
		
	}
	
	public Matricula(Long idMatricula, Double mediaFinal, Double frequencia, StatusMatricula statusMatricula,
			Aluno aluno, Turma turma, Set<Avaliacao> avaliacoes ) {
		
		this.idMatricula = idMatricula;
		this.mediaFinal = mediaFinal;
		this.frequencia = frequencia;
		this.statusMatricula = statusMatricula;
		this.aluno = aluno;
		this.turma = turma;
		this.avaliacoes = avaliacoes;
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

	public StatusMatricula getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(StatusMatricula statusMatricula) {
		this.statusMatricula = statusMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}
	
	public Set<Avaliacao> getAvaliacao() {
		return avaliacoes;
	}

	public void setAvaliacao(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
}
