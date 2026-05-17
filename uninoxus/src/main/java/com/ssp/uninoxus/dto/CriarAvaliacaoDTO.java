package com.ssp.uninoxus.dto;

import java.time.LocalDate;

import com.ssp.uninoxus.enums.TipoAvaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record CriarAvaliacaoDTO(
		@NotBlank String descricaoAvaliacao,
	    @NotNull LocalDate data,
	    @NotNull Long idTurma,
	    @NotNull TipoAvaliacao tipoAvaliacao 
		) 
	
 {}

