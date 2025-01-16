-- Criação do banco de dados
-- CREATE DATABASE booksontheweb;
BEGIN;

-- Conexão ao banco de dados
-- \c booksontheweb;

-- Tabela de Usuários
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela usuario...';
END $$;

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    penalidade INTEGER DEFAULT 0,
    status BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela usuario criada com sucesso ...';
END $$;

-- Tabela de Status de Livros
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela status_livro...';
END $$;

CREATE TABLE status_livro (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela status_livro criada com sucesso ...';
END $$;

-- Tabela de Livros
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela livro...';
END $$;

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    status_id INTEGER NOT NULL REFERENCES status_livro(id) ON DELETE CASCADE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela livro criada com sucesso ...';
END $$;

-- Tabela de Empréstimos
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela emprestimo...';
END $$;

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    livro_id INTEGER NOT NULL REFERENCES livro(id) ON DELETE CASCADE,
    data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_devolucao_prevista TIMESTAMP,
    data_devolucao_real TIMESTAMP,
    devolvido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela emprestimo criada com sucesso ...';
END $$;

-- Tabela de Penalidades
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela penalidade...';
END $$;

CREATE TABLE penalidade (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    valor NUMERIC(19, 4) NOT NULL,
    data_aplicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela penalidade criada com sucesso ...';
END $$;

-- Índices para melhorar a performance
DO $$ BEGIN
    RAISE NOTICE 'Criando indices para melhorar a performance ...';
END $$;

CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_livro_titulo ON livro(titulo);
CREATE INDEX idx_livro_autor ON livro(autor);
CREATE INDEX idx_livro_genero ON livro(genero);
CREATE INDEX idx_emprestimo_usuario_id ON emprestimo(usuario_id);
CREATE INDEX idx_emprestimo_livro_id ON emprestimo(livro_id);

DO $$ BEGIN
    RAISE NOTICE 'Indices de: Usuarios, livros e emprestimos criado com sucesso ...';
END $$;

COMMIT;