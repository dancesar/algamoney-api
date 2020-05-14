package com.dancesar.algamoney.api.repository.lancamento;

import com.dancesar.algamoney.api.model.Lancamento;
import com.dancesar.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}