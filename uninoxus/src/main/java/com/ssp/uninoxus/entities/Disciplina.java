package com.ssp.uninoxus.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
	 
	 @OneToMany(mappedBy = "disciplina")
	 private Set<Turma> turmas = new HashSet<>();
	 
	 
	 public Disciplina() {
			 }
	 
	 public Disciplina(Long idDisciplina, String nomeDisciplina, Integer cargaHoraria, Set<Turma> turmas
			, Curso curso) {
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHoraria = cargaHoraria;
		this.curso = curso;
		this.turmas = turmas;
		
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

	 public Curso getCurso() {
		 return curso;
	 }

	 public void setCurso(Curso curso) {
		 this.curso = curso;
	 }

	 public Set<Turma> getTurmas() {
		 return turmas;
	 }

	 public void setTurmas(Set<Turma> turmas) {
		 this.turmas = turmas;
	 }


	 
}
 