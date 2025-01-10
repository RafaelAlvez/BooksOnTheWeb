BEGIN;

-- Inserir status de livros
DO $$ BEGIN
    RAISE NOTICE 'Inserir os dados de status na tabela: status_livros ...';
END $$;

insert into status_livros (id, descricao, criado_em) values (1, 'DISPONIVEL', NOW());
insert into status_livros (id, descricao, criado_em) values (2, 'INDISPONIVEL', NOW());
insert into status_livros (id, descricao, criado_em) values (3, 'EM_EMPRESTIMO', NOW());

DO $$ BEGIN
    RAISE NOTICE 'Dados de status na tabela status_livros inseridos com sucesso ...';
END $$;

-- Inserir usuários
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela usuarios...';
END $$;

INSERT INTO usuarios (nome, email, telefone, penalidades, status) VALUES
('João Silva', 'joao.silva@email.com', '11987654321', 0, TRUE),
('Maria Oliveira', 'maria.oliveira@email.com', '11987654322', 1, TRUE),
('Carlos Souza', 'carlos.souza@email.com', '11987654323', 0, TRUE),
('Ana Lima', 'ana.lima@email.com', '11987654324', 2, TRUE),
('Fernanda Torres', 'fernanda.torres@email.com', '11987654325', 0, FALSE);

-- Inserir livros
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela livros...';
END $$;

INSERT INTO livros (titulo, autor, genero, status_id) VALUES
('O Senhor dos Anéis', 'J.R.R. Tolkien', 'Fantasia', 1),
('1984', 'George Orwell', 'Ficção Científica', 1),
('Dom Casmurro', 'Machado de Assis', 'Romance', 2),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Infantil', 1),
('A Guerra dos Tronos', 'George R.R. Martin', 'Fantasia', 3);

-- Inserir empréstimos
DO $$ BEGIN
    RAISE NOTICE 'Inserindo dados na tabela emprestimos...';
END $$;

INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao, devolvido) VALUES
(1, 1, '2025-01-01', '2025-01-10', FALSE),
(2, 2, '2025-01-02', '2025-01-12', FALSE),
(3, 3, '2025-01-03', '2025-01-15', TRUE),
(4, 4, '2025-01-04', '2025-01-20', TRUE),
(5, 5, '2025-01-05', NULL, FALSE);

COMMIT;