package com.ssp.uninoxus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCursoDTO(
		 @NotNull(message = "Nome curso não pode ser vazia!") 
		String nomeCurso,
		@NotBlank (message = "Carga horária do curso não pode ser vazia!") 
		Integer cargaHorariaTotal
		
		) {

}
  