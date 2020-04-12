create table categoria(
    id serial primary key,
    nome varchar(50) not null
);

insert into categoria (nome) values ('Lazer');
insert into categoria (nome) values ('Alimentacao');
insert into categoria (nome) values ('Supermercado');
insert into categoria (nome) values ('Farmacia');
insert into categoria (nome) values ('Outros');