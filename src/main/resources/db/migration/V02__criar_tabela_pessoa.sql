create table pessoa(
    id serial primary key,
    nome varchar(50) not null,
    logradouro varchar(30) not null,
    numero varchar(5) not null,
    complemento varchar(30) not null,
    bairro varchar(30) not null,
    cep varchar(9) not null,
    cidade varchar(30) not null,
    uf varchar(2) not null,
    ativo boolean not null
);

insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, uf, ativo) values ('Danillo Cesar', 'Av. Elisio Texeira Leite', '960', 'Bloco 3 Apto 18', 'Freguesia do O', '02801-000', 'Sao Paulo', 'SP', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, uf, ativo) values ('Danillo Cesar', 'Rua Jose Debieux', '145', 'Esquina Rua Sem Saida', 'Santana', '02038-030', 'Sao Paulo', 'SP', false);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, uf, ativo) values ('Homer Simpsom', 'Rua FOX', '631', 'Bloco 2 Apto 45', 'Vila Olimpia', '02038-030', 'Sao Paulo', 'SP', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, uf, ativo) values ('Bart Simpson', 'Av. Pampulha', '145', 'Casa 2', 'Pampulha', '02038-030', 'Belo Horizonte', 'BH', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, uf, ativo) values ('Lisa Simpson', 'Rua Leblom', '145', 'Apto 69', 'Leblom', '02038-030', 'Rio de Janeiro', 'RJ', false);