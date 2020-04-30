package com.dancesar.algamoney.api.service;

import com.dancesar.algamoney.api.model.Pessoas;
import com.dancesar.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoas atualizar(Long id, Pessoas pessoas){
        Pessoas pessosSalva = this.pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(pessoas, pessosSalva, "id");
        return pessoaRepository.save(pessosSalva);
    }
}