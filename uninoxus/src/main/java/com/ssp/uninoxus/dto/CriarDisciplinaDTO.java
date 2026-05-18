package com.ssp.uninoxus.dto;
import jakarta.validation.constraints.NotBlank;


public record CriarDisciplinaDTO(
		@NotBlank(message = "nome da disciplina  não pode ser vazia") 
		String nomeDisciplina,
		@NotBlank
		Integer cargaHoraria,
		@NotBlank (message = "A disciplina deve perterncer a um curso") 
		Long idCurso
		) {

}
 