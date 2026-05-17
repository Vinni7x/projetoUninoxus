package com.ssp.uninoxus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.LancarNotaDTO;
import com.ssp.uninoxus.dto.NotaResponseDTO;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Nota;
import com.ssp.uninoxus.enums.StatusTurma;
import com.ssp.uninoxus.repositories.AvaliacaoRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;
import com.ssp.uninoxus.repositories.NotaRepository;

@Service
public class NotaService {
	
	@Autowired
	private NotaRepository notaRepository;
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;  
	
	 public NotaResponseDTO lancarNota(LancarNotaDTO dto) {
	        Avaliacao avaliacao = avaliacaoRepository.findById(dto.idAvaliacao())
	            .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada!"));

	        Matricula matricula = matriculaRepository.findById(dto.idMatricula())
	            .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada!"));
	        
	        if (matricula.getTurma().getStatusTurma() != StatusTurma.ABERTA) { 
	            throw new IllegalArgumentException("Não é possível lançar nota em uma turma fechada ou consolidada!");
	        } 
	        
	        Nota nota = notaRepository
	        	    .findByAvaliacao_IdAvaliacaoAndMatricula_IdMatricula(dto.idAvaliacao(), dto.idMatricula())
	        	    .orElse(new Nota()); 
	        
	         
	        nota.setNota(dto.nota()); 
	        nota.setAvaliacao(avaliacao);
	        nota.setMatricula(matricula);
 
	        notaRepository.save(nota);
	        return toNotaDTO(nota);
	    } 
	 
	 

	  private NotaResponseDTO toNotaDTO(Nota nota) { 
	        return new NotaResponseDTO(
	            nota.getIdNota(),
	            nota.getNota(),
	            nota.getMatricula().getIdMatricula(),
	            nota.getAvaliacao().getIdAvaliacao()
	        );
	    }
	}


