package com.ssp.uninoxus.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Disciplina {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idDisciplina;
	 private String nomeDisciplina; 
	 private  Integer cargaHoraria; 
	 
	 @ManyToOne
	 @JoinColumn(name = "id_curso") 
	 private Curso curso;
	 
	 
	 public Disciplina() {
			 }
	 
	 public Disciplina(Long idDisciplina, String nomeDisciplina, Integer cargaHoraria) {
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHoraria = cargaHoraria;
	 }

	 public String getNomeDisciplina() {
		 return nomeDisciplina;
	 }

	 public void setNomeDisciplina(String nomeDisciplina) {
		 this.nomeDisciplina = nomeDisciplina;
	 }

	 public Integer getCargaHoraria() {
		 return cargaHoraria;
	 }

	 public void setCargaHoraria(Integer cargaHoraria) {
		 this.cargaHoraria = cargaHoraria;
	 }

	 public Long getIdDisciplina() {
		 return idDisciplina;
	 }






	
	 
	 
	 
	 

}
 