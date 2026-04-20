package com.ssp.uninoxus.entities;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacao;
    private String descricaoAvaliacao;
    private LocalDate data;
    private Double nota;
   
    @ManyToOne
    @JoinColumn(name = "id_turma")  
    private Turma turma;
    
    
    public Avaliacao() {
		
	}

	public Avaliacao(Long idAvaliacao, String descricaoAvaliacao, LocalDate data, Double nota) {
		this.idAvaliacao = idAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.data = data;
		this.nota = nota;
	}

	public String getDescricaoAvaliacao() {
		return descricaoAvaliacao;
	}

	public void setDescricaoAvaliacao(String descricaoAvaliacao) {
		this.descricaoAvaliacao = descricaoAvaliacao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	
    
    
    

}
