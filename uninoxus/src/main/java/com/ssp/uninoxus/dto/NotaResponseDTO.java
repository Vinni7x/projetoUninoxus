package com.ssp.uninoxus.dto;

public record NotaResponseDTO(
	    Double nota,
	    String nomeAluno,
	    String nomeDisciplina,
	    Long idAvaliacao
	) {}