package com.ssp.uninoxus.entities;
import com.ssp.uninoxus.enumeration.StatusMatricula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@JoinColumn(name = "id_aluno", nullable = false)
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name = "id_turma", nullable = false)
	private Turma turma;

	public Matricula() {
		
	}
	
	public Matricula(Long idMatricula, Double mediaFinal, Double frequencia, StatusMatricula statusMatricula,
			Aluno aluno, Turma turma) {
		
		this.idMatricula = idMatricula;
		this.mediaFinal = mediaFinal;
		this.frequencia = frequencia;
		this.statusMatricula = statusMatricula;
		this.aluno = aluno;
		this.turma = turma;
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
	
	
	
}
