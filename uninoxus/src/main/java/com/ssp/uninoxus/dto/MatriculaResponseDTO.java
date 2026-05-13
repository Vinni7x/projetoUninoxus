package com.ssp.uninoxus.dto;

import com.ssp.uninoxus.enums.StatusMatricula;

public record MatriculaResponseDTO(
	    Long idMatricula,
	    Double mediaFinal,
	    Double frequencia,
	    StatusMatricula statusMatricula
	) {} 