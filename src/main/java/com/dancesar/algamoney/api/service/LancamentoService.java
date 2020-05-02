package com.dancesar.algamoney.api.service;

import com.dancesar.algamoney.api.model.Lancamento;
import com.dancesar.algamoney.api.model.Pessoas;
import com.dancesar.algamoney.api.repository.LancamentoRepository;
import com.dancesar.algamoney.api.repository.PessoaRepository;
import com.dancesar.algamoney.api.service.exception.PessoaInexistenteOuInativoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoas> pessoas = pessoaRepository.findById(lancamento.getPessoas().getId());
        if(pessoas == null || pessoas.get().isInativo()){
            throw new PessoaInexistenteOuInativoException();
        }
        return lancamentoRepository.save(lancamento);
    }
}