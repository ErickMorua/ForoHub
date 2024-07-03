package com.forohub.demo.domain.curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarCursoDTO(
        @NotNull
        Long id,
        String nombre,
        Categoria categoria
) {
}
