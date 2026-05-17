package com.ssp.uninoxus.dto;

import java.time.LocalDate;



public record CriarAlunoDTO(
		String nomePessoa,
		String cpf,
		LocalDate dataNascimento,
		Double redimentoAcademico,
		Long idCurso
		
		) {

}
