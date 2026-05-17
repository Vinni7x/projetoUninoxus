package com.ssp.uninoxus.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

	public record LancarNotaDTO(
		    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
		    @NotNull(message = "Nota não pode ser vazia!")
		    Double nota,
		    @NotNull(message = "Matricula não pode ser vazia!")
		    Long idMatricula,
		    @NotNull(message = "Id da Avaliacao não pode ser vazio!")
		    Long idAvaliacao
		) {

} 
    