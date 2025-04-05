CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR NOT NULL
);

CREATE TABLE tribunal (
    id UUID PRIMARY KEY,
    uf CHAR(2) NOT NULL,
    nome_uf VARCHAR NOT NULL,
    sigla_tribunal VARCHAR NOT NULL,
    nome_tribunal VARCHAR NOT NULL,
    url_tribunal VARCHAR,
    status VARCHAR CHECK (status IN ('ATIVO', 'DESATIVADO'))
);

CREATE TABLE cadastro_publicacao (
    id UUID PRIMARY KEY,
    inscricao_oab VARCHAR NOT NULL,
    uf_oab CHAR(2) NOT NULL,
    nome_advogado VARCHAR NOT NULL
    validacao VARCHAR CHECK (status IN ('MANUAL', 'ALTOMATICO', 'REVISADO'))
);

CREATE TABLE cadastro_publicacao_tribunal (
    id UUID PRIMARY KEY,
    cadastro_publicacao_id UUID NOT NULL,
    tribunal_id UUID NOT NULL,

    FOREIGN KEY (cadastro_publicacao_id) REFERENCES cadastro_publicacao(id),
    FOREIGN KEY (tribunal_id) REFERENCES tribunal(id)
);

CREATE TABLE cadastro_publicacao_usuario (
    id UUID PRIMARY KEY,
    cadastro_publicacao_id UUID NOT NULL,
    usuario_id UUID NOT NULL,

    FOREIGN KEY (cadastro_publicacao_id) REFERENCES cadastro_publicacao(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);




CREATE TABLE resultado_oab_tipo (
    id UUID PRIMARY KEY,
    descricao VARCHAR,
    ordem INTEGER
);

CREATE TABLE resultado_oab_organizacao (
    id UUID PRIMARY KEY,
    id_organizacao INTEGER,
    nome_alternativo VARCHAR,
    ordem VARCHAR,
    pagina VARCHAR,
    local VARCHAR
);

CREATE TABLE resultado_oab_setor (
    id UUID PRIMARY KEY,
    resultado_oab_organizacao_id UUID NOT NULL,
    setor INTEGER,
    nome_alternativo VARCHAR,
    nome_original VARCHAR,
    ordem INTEGER,
    FOREIGN KEY (resultado_oab_organizacao_id) REFERENCES resultado_oab_organizacao(id)
);

CREATE TABLE resultado_oab (
    id UUID PRIMARY KEY,
    cadastro_publicacao_id UUID NOT NULL,
    tribunal_id UUID NOT NULL,
    resultado_oab_tipo_id UUID,
    resultado_oab_setor_id UUID,
    id_publicacao INTEGER,
    ano VARCHAR,
    issn VARCHAR,
    titulo TEXT,
    conteudo TEXT,
    conteudo_codificado TEXT,
    local_publicacao VARCHAR,
    data_publicacao DATE,
    pagina INTEGER,
    edicao INTEGER,
    edicao_extra VARCHAR,
    FOREIGN KEY (resultado_oab_tipo_id) REFERENCES resultado_oab_tipo(id),
    FOREIGN KEY (resultado_oab_setor_id) REFERENCES resultado_oab_setor(id),
    FOREIGN KEY (cadastro_publicacao_id) REFERENCES cadastro_publicacao(id),
    FOREIGN KEY (tribunal_id) REFERENCES tribunal(id)
);
