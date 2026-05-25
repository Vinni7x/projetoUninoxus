package com.ssp.uninoxus.dto;

import com.ssp.uninoxus.enums.StatusMatricula;

public record NotaAlunoDTO(
		 	String nomeDisciplina,
		    Double av1,
		    Double av2,
		    Double av3,
		    Double reposicao,
		    Double finalNota,
		    Double mediaFinal,
		    StatusMatricula situacao 
		) {

}
