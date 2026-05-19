package com.ssp.uninoxus.dto;

import jakarta.validation.constraints.NotNull;

public record CriarMatriculaDTO(
		@NotNull
		Long matriculaAluno,
		@NotNull
		Long idTurma
		
		) {

}  
 