package com.ssp.uninoxus.dto;

import java.time.LocalDate;

public record AvaliacaoResponseDTO(
	    Long idAvaliacao,
	    String descricaoAvaliacao,
	    LocalDate data,
	    Double nota,              
	    Long idMatricula
	) {}
  