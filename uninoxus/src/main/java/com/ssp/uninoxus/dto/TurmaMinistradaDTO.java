package com.ssp.uninoxus.dto;

import java.time.LocalTime;
import java.util.List;

import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.Turno;

public record TurmaMinistradaDTO(
		Long idTurma,
	    String nomeDisciplina,
	    Turno turno,
	    LocalTime horarioInicio,
	    LocalTime horarioFinal,
	    String local,
	    List<DiasSemana> diasSemana,
	    StatusTurma statusTurma) {

}
