package com.ssp.uninoxus.dto;

public record NotaResponseDTO(
	    Long idNota,
	    Double nota,
	    Long idMatricula,
	    Long idAvaliacao
	) {}