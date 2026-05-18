package com.ssp.uninoxus.dto;
import java.time.LocalDate;

import com.ssp.uninoxus.enums.Titulacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarProfessorDTO(
		@NotBlank
		String nomePessoa,
		@NotBlank
		String cpf,
		@NotNull
		LocalDate dataNascimento,
		@NotNull
		Titulacao titulacao,
		@NotBlank
		String especializacao,
		@NotNull
		Long idCurso
		
		
		) { 
	
}
