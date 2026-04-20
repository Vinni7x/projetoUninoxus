package com.ssp.uninoxus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso") 
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
    private String nomeCurso;
    private Integer cargaHorariaTotal;
   
    
    
    
    
	public Curso() {
		
	}

	public Curso(Long idCurso, String nomeCurso, Integer cargaHorariaTotal) {
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Integer getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Long getIdCurso() {
		return idCurso;
	}
    
    
}
