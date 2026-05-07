package com.ssp.uninoxus.dto;

import java.time.LocalDate;

public record AlunoResponseDTO(
	    Long matriculaAluno,
	    String nomePessoa,
	    String cpf,
	    LocalDate dataNascimento,
	    Double rendimentoAcademico,
	    Long idCurso
	) {} 