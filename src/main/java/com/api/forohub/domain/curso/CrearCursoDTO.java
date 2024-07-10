package com.api.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotNull
        String nombre,
        @NotNull
        Categoria categoria
) {
}
