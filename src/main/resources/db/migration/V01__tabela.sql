create table oportunidade(
 id bigint auto_increment not null, 
 nome varchar(80) not null,
 descricao varchar(200) not null,
 valor decimal(10,2), 
 primary key (id)
);