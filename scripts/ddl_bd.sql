create table clientes
(
    idCliente   integer not null
        primary key autoincrement,
    nomeCliente varchar not null
);

create table projetos
(
    idProjeto            integer not null
        primary key autoincrement,
    nomeProjeto          varchar not null,
    localizacaoProjeto   varchar not null,
    escopoInicialProjeto text    not null,
    dataInicialProjeto   text    not null,
    clienteProjeto       int     not null
        references clientes,
    isFinalizado         boolean not null
);