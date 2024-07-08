package com.forohub.demo.domain.topico;

import com.forohub.demo.domain.topico.respuesta.RespuestaDTO;
import org.springframework.data.domain.Page;

public record TopicoRespuestaDTO(TopicoDTO topico, Page<RespuestaDTO> respuestas) {
}
