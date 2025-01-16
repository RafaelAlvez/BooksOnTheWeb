package br.com.fiap._aoj.atom.livros.mapper;

import br.com.fiap._aoj.atom.livros.entity.LivroEntity;
import br.com.fiap._aoj.atom.livros.entity.StatusLivro;
import br.com.fiap.aoj.atom.livros.model.Livro;
import br.com.fiap.aoj.atom.livros.model.LivroInput;
import br.com.fiap.aoj.atom.livros.model.LivroOutput;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ResourceMapper {

    public List<Livro> toListDto(List<LivroEntity> livroEntity) {
        return livroEntity.stream()
                .map(ResourceMapper::toLivroObject)
                .collect(Collectors.toList());
    }

    public Livro toModel(LivroEntity lEntity) {
        return toLivroObject(lEntity);
    }

    private Livro toLivroObject(LivroEntity le) {
        return new Livro()
                .id(le.getId())
                .titulo(le.getTitulo())
                .autor(le.getAutor())
                .genero(le.getGenero())
                .status(Livro.StatusEnum.fromValue(le.getStatus().name()));
    }

    public LivroEntity toEntity(LivroInput l) {
        var le = new LivroEntity();
        le.setTitulo(l.getTitulo());
        le.setAutor(l.getAutor());
        le.setGenero(l.getGenero());
        le.setStatus(StatusLivro.fromNome(l.getStatus().name()));
        le.setCriadoEm(OffsetDateTime.now());
        le.setAtualizadoEm(null);
        return le;
    }

    public LivroOutput toOutoput(LivroEntity le) {
        return new LivroOutput()
                .id(le.getId())
                .titulo(le.getTitulo())
                .autor(le.getAutor())
                .genero(le.getGenero())
                .status(LivroOutput.StatusEnum.fromValue(le.getStatus().name()));
    }

    public LivroEntity toEntity(LivroEntity livroEntity, LivroInput livroInput) {
        return livroEntity;

    }

}