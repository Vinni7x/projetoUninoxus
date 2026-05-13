package com.ssp.uninoxus.dto;

import java.time.LocalTime;
import java.util.List;

import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.Turno;



public record TurmaResponseDTO(
		Long idTurma,
		String semestre,
		Turno turno,  
	    LocalTime horarioInicio,
	    LocalTime horarioFinal,
	    String local,
	    Integer vagas,
	    List<DiasSemana> diasSemana,
	    StatusTurma statusTurma,
	    Long idCurso,
	    Long idDisciplina,
	    Long MatriculaProfessor) {

}
