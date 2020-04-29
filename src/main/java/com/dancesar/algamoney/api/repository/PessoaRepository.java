package com.dancesar.algamoney.api.repository;

import com.dancesar.algamoney.api.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
}