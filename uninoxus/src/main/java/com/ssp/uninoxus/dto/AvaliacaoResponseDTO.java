package com.ssp.uninoxus.dto;

import java.time.LocalDate;


import com.ssp.uninoxus.enums.TipoAvaliacao;

public record AvaliacaoResponseDTO(
		String descricaoAvaliacao,
		LocalDate data,
		TipoAvaliacao tipoAvaliacao,
		String nomeDisciplina
	) {

}
 