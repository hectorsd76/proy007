    drop table if exists entrada cascade 
    drop sequence if exists entrada_seq
    create sequence entrada_sql start with 1 increment by 50
    create table entrada (
        numAsiento int,
        partido varchar(255),
        idEntrada bigint not null,
        fechaPartido Date,
        primary key (idEntrada)
    )