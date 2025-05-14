CREATE TABLE usuario (
	id serial4 NOT NULL,
	nome varchar(100) NOT NULL,
	login varchar(50) NOT NULL,
	senha varchar(100) NOT NULL,
	nivel_acesso varchar(20) NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	data_ultimo_login timestamp NULL,
	CONSTRAINT usuario_login_key UNIQUE (login),
	CONSTRAINT usuario_nivel_acesso_check CHECK (((nivel_acesso)::text = ANY ((ARRAY['ADMIN'::character varying, 'OPERADOR'::character varying])::text[]))),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE produto (
	id serial4 NOT NULL,
	codigo_barras varchar(13) NOT NULL,
	nome varchar(200) NOT NULL,
	descricao text NULL,
	preco numeric(10, 2) NOT NULL,
	data_validade date NULL,
	categoria_id int4 NULL,
	usuario_id int4 NULL,
	data_cadastro date DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT preco_positivo CHECK ((preco > (0)::numeric)),
	CONSTRAINT produto_codigo_barras_key UNIQUE (codigo_barras),
	CONSTRAINT produto_pkey PRIMARY KEY (id),
	CONSTRAINT unique_codigo_barras UNIQUE (codigo_barras)
);

CREATE TABLE categoria (
	id serial4 NOT NULL,
	nome varchar(50) NOT NULL,
	descricao text NULL,
	CONSTRAINT categoria_nome_key UNIQUE (nome),
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);

CREATE TABLE etiqueta (
	id serial4 NOT NULL,
	produto_id int4 NOT NULL,
	configuracao_id int4 NOT NULL,
	preco numeric(10, 2) NOT NULL,
	data_validade date NULL,
	usuario_id int4 NULL,
	data_geracao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT etiqueta_pkey PRIMARY KEY (id)
);

CREATE TABLE public.configuracao_etiqueta (
	id serial4 NOT NULL,
	nome varchar(50) NOT NULL,
	descricao text NULL,
	logo_path varchar(255) NULL,
	fonte varchar(30) DEFAULT 'Arial'::character varying NULL,
	tamanho_fonte int4 DEFAULT 12 NULL,
	cor_fonte varchar(7) DEFAULT '#000000'::character varying NULL,
	cor_fundo varchar(7) DEFAULT '#FFFFFF'::character varying NULL,
	largura_mm int4 NOT NULL,
	altura_mm int4 NOT NULL,
	margem_superior_mm int4 DEFAULT 2 NULL,
	margem_inferior_mm int4 DEFAULT 2 NULL,
	usuario_id int4 NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT configuracao_etiqueta_pkey PRIMARY KEY (id),
	CONSTRAINT cor_valida CHECK ((((cor_fonte)::text ~ '^#[0-9A-Fa-f]{6}$'::text) AND ((cor_fundo)::text ~ '^#[0-9A-Fa-f]{6}$'::text)))
);

CREATE TABLE public.historico_etiqueta (
	id serial4 NOT NULL,
	etiqueta_id int4 NOT NULL,
	usuario_id int4 NULL,
	data_impressao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	quantidade_impressoes int4 DEFAULT 1 NULL,
	impressora_utilizada varchar(100) NULL,
	CONSTRAINT historico_etiqueta_pkey PRIMARY KEY (id)
);

-- Tabela produto
ALTER TABLE produto
ADD CONSTRAINT fk_produto_categoria
FOREIGN KEY (categoria_id) REFERENCES categoria(id);

ALTER TABLE produto
ADD CONSTRAINT fk_produto_usuario
FOREIGN KEY (usuario_id) REFERENCES usuario(id);

-- Tabela etiqueta
ALTER TABLE etiqueta
ADD CONSTRAINT fk_etiqueta_produto
FOREIGN KEY (produto_id) REFERENCES produto(id);

ALTER TABLE etiqueta
ADD CONSTRAINT fk_etiqueta_configuracao
FOREIGN KEY (configuracao_id) REFERENCES configuracao_etiqueta(id);