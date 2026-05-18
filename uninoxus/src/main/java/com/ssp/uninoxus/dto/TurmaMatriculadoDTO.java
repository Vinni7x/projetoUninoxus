package com.ssp.uninoxus.dto;

import java.time.LocalTime;
import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.enums.Turno;

public record TurmaMatriculadoDTO(
    Turno turno,  
    LocalTime horarioInicio,
    LocalTime horarioFinal,
    String local,
    DiasSemana[] diasSemana,
    StatusTurma statusTurma
) { 
}