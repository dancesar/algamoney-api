package com.dancesar.algamoney.api.repository;

import com.dancesar.algamoney.api.model.Lancamento;
import com.dancesar.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}