CREATE TABLE IF NOT EXISTS conta (
    id bigserial NOT NULL,
    data_criacao timestamp(6) NOT NULL,
    data_atualizacao timestamp(6) NULL,
    valor float8 NULL,
    data_pagamento timestamp(6) NULL,
    data_vencimento timestamp(6) NULL,
    descricao varchar(2000) NULL,
    situacao varchar(100) NULL,
    CONSTRAINT conta_pkey PRIMARY KEY (id)
);