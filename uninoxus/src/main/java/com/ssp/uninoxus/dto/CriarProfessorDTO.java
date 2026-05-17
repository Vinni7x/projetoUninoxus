package com.ssp.uninoxus.dto;
import java.time.LocalDate;
import com.ssp.uninoxus.enums.Titulacao;

public record CriarProfessorDTO(
		String nomePessoa,
		String cpf,
		LocalDate dataNascimento,
		Titulacao titulacao,
		String especializacao,
		Long idCurso
		
		
		) { 
	
}
