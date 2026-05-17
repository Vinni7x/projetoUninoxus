package com.ssp.uninoxus.entities;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssp.uninoxus.enums.TipoAvaliacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacao;
	@Column(nullable = false)
    private String descricaoAvaliacao;
	@Column(nullable = false)
    private LocalDate data;
	@Enumerated(EnumType.STRING)
	TipoAvaliacao tipoAvaliacao; 
	
    @ManyToOne 
    @JoinColumn(name = "id_turma", nullable = false)  
    private Turma turma;
    
    @OneToMany(mappedBy = "avaliacao")
    @JsonIgnore
	private Set<Nota> notas = new HashSet<>(); 
   

	public Avaliacao(Long idAvaliacao, String descricaoAvaliacao, LocalDate data, TipoAvaliacao tipoAvaliacao,
			Turma turma, Set<Nota> notas) {
	
		this.idAvaliacao = idAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.data = data;
		this.tipoAvaliacao = tipoAvaliacao;
		this.turma = turma;
		this.notas = notas;
	}

	public Avaliacao() {
	
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

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Set<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}

}
