package com.ssp.uninoxus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.dto.AvaliacaoResponseDTO;
import com.ssp.uninoxus.dto.CriarAvaliacaoDTO;
import com.ssp.uninoxus.dto.LancarNotaDTO;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.repositories.AvaliacaoRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

 
    public AvaliacaoResponseDTO adicionar(CriarAvaliacaoDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.idMatricula())
            .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada!"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricaoAvaliacao(dto.descricaoAvaliacao());
        avaliacao.setData(dto.data());
        avaliacao.setMatricula(matricula);
      

        avaliacaoRepository.save(avaliacao);
        return toDTO(avaliacao); 
    }


    public AvaliacaoResponseDTO update(Long idAvaliacao, CriarAvaliacaoDTO dto) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(idAvaliacao)
            .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada!"));

        Matricula matricula = matriculaRepository.findById(dto.idMatricula())
            .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada!"));

        avaliacaoExistente.setDescricaoAvaliacao(dto.descricaoAvaliacao());
        avaliacaoExistente.setData(dto.data());
        avaliacaoExistente.setMatricula(matricula);

        avaliacaoRepository.save(avaliacaoExistente);  
        return toDTO(avaliacaoExistente);
    } 


    public AvaliacaoResponseDTO lancaNota(Long idAvaliacao, LancarNotaDTO dto) {
        if (dto.nota() == null) {
            throw new IllegalArgumentException("Nota não pode ser vazia!");
        }

        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(idAvaliacao)
            .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada!"));

        avaliacaoExistente.setNota(dto.nota());
        avaliacaoRepository.save(avaliacaoExistente);
        return toDTO(avaliacaoExistente);
    } 
    
    public List<AvaliacaoResponseDTO> todasProvas(Long idMatricula) {
    	 List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByMatricula_IdMatricula(idMatricula);
    	    
    	    List<AvaliacaoResponseDTO> lista = new ArrayList<>();
    	    for (Avaliacao a : avaliacoes) {
    	        lista.add(toDTO(a));  
    	    }
    	    return lista;
    	}    
     
    
    public void deletar (Long idAvaliacao ){
    	
    	if(idAvaliacao != null && avaliacaoRepository.existsById(idAvaliacao)){
    		avaliacaoRepository.deleteById(idAvaliacao); 
    		}
    	else {
    		throw new IllegalArgumentException("Avaliação não encontrada, impossivel apagar!");
    		}
    	 
    	  
    } 
  
    private AvaliacaoResponseDTO toDTO(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
            avaliacao.getIdAvaliacao(),
            avaliacao.getDescricaoAvaliacao(),
            avaliacao.getData(),
            avaliacao.getNota(),
            avaliacao.getMatricula().getIdMatricula()
        );
    }
    
}