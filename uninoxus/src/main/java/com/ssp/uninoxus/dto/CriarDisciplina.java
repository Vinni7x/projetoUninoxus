package com.ssp.uninoxus.dto;
import com.ssp.uninoxus.entities.Curso;

import jakarta.validation.constraints.NotBlank;


public record CriarDisciplina(
		@NotBlank(message = "nome da disciplina  não pode ser vazia") 
		String nomeDisciplina,
		@NotBlank
		Integer cargaHoraria,
		@NotBlank (message = "A disciplina deve perterncer a um curso") 
		Curso curso
		) {

}
 