package com.api.forohub.domain.curso;

public record CursoDTO(Long id, String nombre, Categoria categoria, Boolean activo) {

    public CursoDTO(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria(), curso.getActivo());
    }
}
