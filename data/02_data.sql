BEGIN;

-- Inserir status de livros
DO $$ BEGIN
    RAISE NOTICE 'Inserir os dados de status na tabela: status_livro ...';
END $$;

insert into status_livro (id, descricao, criado_em) values (1, 'DISPONIVEL', NOW());
insert into status_livro (id, descricao, criado_em) values (2, 'INDISPONIVEL', NOW());
insert into status_livro (id, descricao, criado_em) values (3, 'EM_EMPRESTIMO', NOW());

DO $$ BEGIN
    RAISE NOTICE 'Dados de status na tabela status_livro inseridos com sucesso ...';
END $$;

-- Inserir usuários
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela usuario...';
END $$;

INSERT INTO usuario (nome, email, telefone, penalidade, status) VALUES
('João Silva', 'joao.silva@email.com', '11987654321', 0, TRUE),
('Maria Oliveira', 'maria.oliveira@email.com', '11987654322', 1, TRUE),
('Carlos Souza', 'carlos.souza@email.com', '11987654323', 0, TRUE),
('Ana Lima', 'ana.lima@email.com', '11987654324', 2, TRUE),
('Fernanda Torres', 'fernanda.torres@email.com', '11987654325', 0, FALSE);

-- Inserir livros
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela livro...';
END $$;

INSERT INTO livro (titulo, autor, genero, status_id, criado_em) VALUES
('O Senhor dos Anéis', 'J.R.R. Tolkien', 'Fantasia', 1, now()),
('1984', 'George Orwell', 'Ficção Científica', 1, now()),
('Dom Casmurro', 'Machado de Assis', 'Romance', 2, now()),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Infantil', 1, now()),
('A Guerra dos Tronos', 'George R.R. Martin', 'Fantasia', 3, now());

-- Inserir empréstimos
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela emprestimo...';
END $$;

INSERT INTO emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao_prevista, data_devolucao_real, devolvido) VALUES
(1, 1, '2025-01-01', '2025-01-10', '2025-01-10', FALSE),
(2, 2, '2025-01-02', '2025-01-12', '2025-01-10', FALSE),
(3, 3, '2025-01-03', '2025-01-15', '2025-01-19', TRUE),
(4, 4, '2025-01-04', '2025-01-20', '2025-01-20', TRUE),
(5, 5, '2025-01-05', NULL, NULL, FALSE);

COMMIT;