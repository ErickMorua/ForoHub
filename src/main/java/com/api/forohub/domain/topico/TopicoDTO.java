package com.api.forohub.domain.topico;

import java.time.LocalDateTime;

public record TopicoDTO(Long id,
                        String titulo,
                        String mensaje,
                        LocalDateTime fecha,
                        Boolean status,
                        String autor,
                        String curso) {

    public TopicoDTO(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutorTopico().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
