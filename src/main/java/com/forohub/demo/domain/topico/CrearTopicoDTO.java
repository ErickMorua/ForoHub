package com.forohub.demo.domain.topico;

import jakarta.validation.constraints.NotNull;

public record CrearTopicoDTO(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso
) {
}
