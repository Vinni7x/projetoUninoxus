package com.ssp.uninoxus.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record CriarAlunoDTO(
		@NotBlank
		String nomePessoa,
		@NotBlank
		String cpf,
		@NotNull
		LocalDate dataNascimento,
		@NotNull
		Long idCurso
		
		) {

}
