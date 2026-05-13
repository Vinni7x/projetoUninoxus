package com.ssp.uninoxus.dto;

import java.time.LocalDate;

import com.ssp.uninoxus.enums.TipoAvaliacao;

public record AvaliacaoResponseDTO(
	    Long idAvaliacao,
	    String descricaoAvaliacao,
	    LocalDate data,
	    Double nota,              
	    Long idMatricula,
	    TipoAvaliacao tipoAvaliacao 
	) {}
  