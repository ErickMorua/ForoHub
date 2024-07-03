package com.forohub.demo.domain.curso;

import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotNull
        String nombre,
        @NotNull
        Categoria categoria
) {
}
