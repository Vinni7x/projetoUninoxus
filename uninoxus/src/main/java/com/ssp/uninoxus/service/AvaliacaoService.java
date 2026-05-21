package com.ssp.uninoxus.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ssp.uninoxus.dto.AvaliacaoResponseDTO;
import com.ssp.uninoxus.dto.CriarAvaliacaoDTO;
import com.ssp.uninoxus.entities.Avaliacao;
import com.ssp.uninoxus.entities.Matricula;
import com.ssp.uninoxus.entities.Turma;
import com.ssp.uninoxus.repositories.AvaliacaoRepository;
import com.ssp.uninoxus.repositories.MatriculaRepository;
import com.ssp.uninoxus.repositories.TurmaRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

  
    public AvaliacaoResponseDTO adicionar(CriarAvaliacaoDTO dto) {
        Turma turma = turmaRepository.findById(dto.idTurma())
            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        boolean jaExiste = avaliacaoRepository.existsByTurma_IdTurmaAndTipoAvaliacao( 
            dto.idTurma(),
            dto.tipoAvaliacao() 
        );
        if (jaExiste) {
            throw new IllegalArgumentException(
                "Já existe uma avaliação do tipo " + dto.tipoAvaliacao() + " para essa turma!"
            );
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricaoAvaliacao(dto.descricaoAvaliacao());
        avaliacao.setTipoAvaliacao(dto.tipoAvaliacao());
        avaliacao.setData(dto.data());
        avaliacao.setTurma(turma);

        avaliacaoRepository.save(avaliacao);
        return toDTO(avaliacao);
    }

    public AvaliacaoResponseDTO update(Long idAvaliacao, CriarAvaliacaoDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao) 
            .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada!"));

        Turma turma = turmaRepository.findById(dto.idTurma())
            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        avaliacao.setDescricaoAvaliacao(dto.descricaoAvaliacao());
        avaliacao.setData(dto.data());
        avaliacao.setTurma(turma);

        avaliacaoRepository.save(avaliacao); 
        return toDTO(avaliacao);
    }

    public List<AvaliacaoResponseDTO> avaliacoesDoAluno(Long matriculaAluno) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByTurma_Matriculas_Aluno_MatriculaAluno(matriculaAluno);

        List<AvaliacaoResponseDTO> lista = new ArrayList<>();
        for (Avaliacao a : avaliacoes) {
            lista.add(new AvaliacaoResponseDTO(
                a.getDescricaoAvaliacao(),
                a.getData(),
                a.getTipoAvaliacao(),
                a.getTurma().getDisciplina().getNomeDisciplina()
            )); 
        }
        
        return lista;
    }
    
    
    public List<AvaliacaoResponseDTO> avaliacoesDoProfessor(Long matriculaProfessor) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByTurma_Professor_MatriculaProfessor(matriculaProfessor);

        List<AvaliacaoResponseDTO> lista = new ArrayList<>();
        for (Avaliacao a : avaliacoes) {
            lista.add(new AvaliacaoResponseDTO(
                a.getDescricaoAvaliacao(),
                a.getData(),
                a.getTipoAvaliacao(),
                a.getTurma().getDisciplina().getNomeDisciplina() 
            )); 
        }
         
        return lista;
    }

    public void deletar(Long idAvaliacao) {
        if (!avaliacaoRepository.existsById(idAvaliacao)) {
            throw new IllegalArgumentException("Avaliação não encontrada, impossível apagar!");
        }
        avaliacaoRepository.deleteById(idAvaliacao);
    }

    private AvaliacaoResponseDTO toDTO(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
           
            avaliacao.getDescricaoAvaliacao(),
            avaliacao.getData(), 
            avaliacao.getTipoAvaliacao(),
            avaliacao.getTurma().getDisciplina().getNomeDisciplina()
          
            
        );  
    }
}