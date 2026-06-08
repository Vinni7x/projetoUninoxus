package com.ssp.uninoxus.dto;


public record ProfessorResponseDTO(
		Long matriculaProfessor,
		String nomePessoa,
		String cpf,
		String nomeCurso,
		Long matriculaAluno
		) {
	
}
