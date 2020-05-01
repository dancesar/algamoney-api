package com.dancesar.algamoney.api.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoas pessoas;

}