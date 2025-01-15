-- Criação do banco de dados
-- CREATE DATABASE booksontheweb;
BEGIN;

-- Conexão ao banco de dados
\c booksontheweb;

-- Tabela de Usuários
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela usuarios...';
END $$;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    penalidades INTEGER DEFAULT 0,
    status BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela usuarios criada com sucesso ...';
END $$;

-- Tabela de Status de Livros
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela status_livros...';
END $$;

CREATE TABLE status_livros (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela status_livros criada com sucesso ...';
END $$;

-- Tabela de Livros
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela livros...';
END $$;

CREATE TABLE livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    status_id INTEGER NOT NULL REFERENCES status_livros(id) ON DELETE CASCADE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela livros criada com sucesso ...';
END $$;

-- Tabela de Empréstimos
DO $$ BEGIN
    RAISE NOTICE 'Criando a tabela emprestimos...';
END $$;

CREATE TABLE emprestimos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    livro_id INTEGER NOT NULL REFERENCES livros(id) ON DELETE CASCADE,
    data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_devolucao TIMESTAMP,
    devolvido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$ BEGIN
    RAISE NOTICE 'Tabela emprestimos criada com sucesso ...';
END $$;

-- Índices para melhorar a performance
DO $$ BEGIN
    RAISE NOTICE 'Criando indices para melhorar a performance ...';
END $$;

CREATE INDEX idx_usuario_email ON usuarios(email);
CREATE INDEX idx_livro_titulo ON livros(titulo);
CREATE INDEX idx_livro_autor ON livros(autor);
CREATE INDEX idx_livro_genero ON livros(genero);
CREATE INDEX idx_emprestimo_usuario_id ON emprestimos(usuario_id);
CREATE INDEX idx_emprestimo_livro_id ON emprestimos(livro_id);

DO $$ BEGIN
    RAISE NOTICE 'Indices de: Usuarios, livros e emprestimos criado com sucesso ...';
END $$;

COMMIT;