package com.ssp.uninoxus.dto;
import java.time.LocalTime;
import java.util.List;
import com.ssp.uninoxus.enums.DiasSemana;
import com.ssp.uninoxus.enums.Turno;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarTurmaDTO(
		@NotBlank String semestre,
	    @NotNull Turno turno, 
	    @NotNull LocalTime horarioInicio,
	    @NotNull LocalTime horarioFinal,
	    @NotBlank String local,
	    @Min(1) Integer vagas,
	    @NotEmpty(message = "Selecione pelo menos um dia da semana!") 
	    List<DiasSemana> diasSemana,
	    @NotNull Long idCurso,
	    @NotNull Long idDisciplina,
	    @NotNull Long matriculaProfessor) {

}
 