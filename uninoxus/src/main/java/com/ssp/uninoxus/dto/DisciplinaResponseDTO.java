package com.ssp.uninoxus.dto;

public record DisciplinaResponseDTO(
	    Long idDisciplina,
	    String nomeDisciplina,
	    Integer cargaHoraria,
	    Long idCurso
	) {}
