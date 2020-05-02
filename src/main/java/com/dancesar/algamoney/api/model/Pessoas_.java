package com.dancesar.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoas.class)
public abstract class Pessoas_ {

	public static volatile SingularAttribute<Pessoas, Boolean> ativo;
	public static volatile SingularAttribute<Pessoas, Endereco> endereco;
	public static volatile SingularAttribute<Pessoas, String> nome;
	public static volatile SingularAttribute<Pessoas, Long> id;

	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

