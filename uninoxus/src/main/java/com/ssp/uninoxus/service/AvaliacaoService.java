package com.ssp.uninoxus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;  

	 
	
	
	public Avaliacao adicionar (Avaliacao avaliacao) { 
		
		if (avaliacao.getNota() == null) {
	        avaliacao.setNota(0.0);
	    } 
		return avaliacaoRepository.save(avaliacao);
	}
	
	public void lancaNota(Avaliacao avaliacao) throws  IllegalArgumentException {

	    
	    if (avaliacao.getNota() == null || avaliacao.getNota().toString().isBlank()) {
	        throw new  IllegalArgumentException ("Não é possível lançar a nota: o campo está vazio!");
	    } 

	   
	    Avaliacao avaliacaoExistente = this.avaliacaoRepository
	            .findById(avaliacao.getIdAvaliacao())
	            .orElseThrow(() -> new  IllegalArgumentException("Avaliação não encontrada!"));

	
	    avaliacaoExistente.setNota(avaliacao.getNota());
 
	    this.avaliacaoRepository.save(avaliacaoExistente);
	}
	
}   
