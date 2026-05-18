package com.ssp.uninoxus.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CriarDisciplinaDTO(
		@NotBlank(message = "nome da disciplina  não pode ser vazia") 
		String nomeDisciplina,
		@NotNull
		Integer cargaHoraria,
		@NotNull (message = "A disciplina deve perterncer a um curso") 
		Long idCurso
		) {

}
 