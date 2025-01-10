BEGIN;

DO $$ BEGIN
    RAISE NOTICE 'Inserir os dados de status na tabela: status_livros ...';
END $$;

insert into status_livros (id, descricao, criado_em) values (1, 'DISPONIVEL', NOW());
insert into status_livros (id, descricao, criado_em) values (2, 'INDISPONIVEL', NOW());
insert into status_livros (id, descricao, criado_em) values (3, 'EM_EMPRESTIMO', NOW());

DO $$ BEGIN
    RAISE NOTICE 'Dados de status na tabela status_livros inseridos com sucesso ...';
END $$;

COMMIT;