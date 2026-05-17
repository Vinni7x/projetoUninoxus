package com.ssp.uninoxus.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notas")
public class Nota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNota;
	private Double nota;
	
	@ManyToOne
	@JoinColumn(name = "id_matricula")
	private Matricula matricula;
	@ManyToOne
	@JoinColumn(name = "id_avaliacao")
	private Avaliacao avaliacao;
	
	
	public Nota(Long idNota, Double nota, Matricula matricula, Avaliacao avaliacao) {
		
		this.idNota = idNota;
		this.nota = nota;
		this.matricula = matricula;
		this.avaliacao = avaliacao; 
	}


	public Nota() {

	}


	public Double getNota() {
		return nota;
	}


	public void setNota(Double nota) {
		this.nota = nota;
	}


	public Matricula getMatricula() {
		return matricula;
	}


	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	} 


	public Long getIdNota() {
		return idNota;
	}
	
	

}
